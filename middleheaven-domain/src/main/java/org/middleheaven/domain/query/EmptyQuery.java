package org.middleheaven.domain.query;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;

/**
 * 
 * @param <T>
 */
public final class EmptyQuery<T> implements QueryResult<T>{

	@Override
	public long count() {
		return 0;
	}

	@Override
	public T fetchFirst() {
		return null;
	}

	@Override
	public Enumerable<T> fetchAll() {
		return Enumerables.emptyEnumerable();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public QueryResult<T> limit(int startAt, int maxCount) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return Collections.<T>emptyList().iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> maybeFetchFirst() {
		return Optional.empty();
	}

}
