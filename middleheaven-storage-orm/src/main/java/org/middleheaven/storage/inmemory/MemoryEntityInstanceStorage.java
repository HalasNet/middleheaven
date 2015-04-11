/**
 * 
 */
package org.middleheaven.storage.inmemory;

import java.util.Collection;
import java.util.Collections;

import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.domain.criteria.EntityCriteria;
import org.middleheaven.domain.query.EnumerableQuery;
import org.middleheaven.domain.query.QueryResult;
import org.middleheaven.domain.store.DomainStoreManager;
import org.middleheaven.domain.store.EntityInstance;
import org.middleheaven.domain.store.EntityInstanceStorage;
import org.middleheaven.util.criteria.ReadStrategy;

/**
 * 
 */
public class MemoryEntityInstanceStorage implements EntityInstanceStorage {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Collection<EntityInstance> obj) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Collection<EntityInstance> obj) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Collection<EntityInstance> obj) {
	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> void remove(EntityCriteria<T> criteria) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> QueryResult<T> createQuery(EntityCriteria<T> criteria,
			ReadStrategy strategy) {
		return new EnumerableQuery<T>(Enumerables.emptyEnumerable());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setStorableStateManager(DomainStoreManager stateManager) {
		
	}

}
