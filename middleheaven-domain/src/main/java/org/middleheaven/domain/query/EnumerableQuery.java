package org.middleheaven.domain.query;

import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.enumerable.size.CountableEnumerableSize;
import org.middleheaven.util.Maybe;


public class EnumerableQuery<T> implements QueryResult<T> {
	
	private Callable<Enumerable<T>> callable;
	
	public EnumerableQuery(Callable<Enumerable<T>> callable) {
		super();
		this.callable= callable;
	}
	
	public EnumerableQuery(final Enumerable<T> other) {
		super();
		this.callable= () -> other;
	}

	private Enumerable<T> secureEnumerable(){
		Enumerable<T> list;
		try {
			list = this.callable.call();
			if (list == null){
				return Enumerables.emptyEnumerable();
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public long count() {
		return Maybe.of(secureEnumerable().getSizeInfo()).maybeCast(CountableEnumerableSize.class).map(s -> s.getValue().asLong()).or(-1L);
	}

	@Override
	public T fetchFirst() {
		final Enumerable<T> list = secureEnumerable();
		if (list.isEmpty()){
			return null;
		}
		return list.findFirst();
	}
	
	@Override
	public Optional<T> maybeFetchFirst() {
		final Enumerable<T> list = secureEnumerable();
		if (list.isEmpty()){
			return Optional.empty();
		}
		return list.tryFirst();
	}

	@Override
	public Enumerable<T> fetchAll() {
		return Enumerables.asEnumerable(secureEnumerable());
	}

	@Override
	public boolean isEmpty() {
		return secureEnumerable().isEmpty();
	}

	@Override
	public QueryResult<T> limit(final int startAt, final int maxCount) {
		
		if (startAt < 1){
			throw new IllegalArgumentException("Range must start at position 1 or further");
		}
		
		return new EnumerableQuery<T>( ()-> {	
			final Enumerable<T> list = EnumerableQuery.this.secureEnumerable();
			return list.skip(startAt-1).take(Math.min(count(),startAt+maxCount-1));
		});
				
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return secureEnumerable().iterator();
	}

}
