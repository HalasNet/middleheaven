/**
 * 
 */
package org.middleheaven.domain.query;

import java.util.Iterator;
import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;

/**
 * 
 */
public class SingleQueryResult<T> implements QueryResult<T> {

	private T obj;

	public SingleQueryResult(T obj){
		this.obj  = obj;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T fetchFirst() {
		return obj;
	}
	
	public Optional<T> maybeFetchFirst() {
		return Optional.ofNullable(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> fetchAll() {
		return obj == null ? Enumerables.emptyEnumerable() : Enumerables.asEnumerable(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return obj == null ? 0L : 1L;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		 return obj == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QueryResult<T> limit(int startAt, int maxCount) {
		return this;
	}

}
