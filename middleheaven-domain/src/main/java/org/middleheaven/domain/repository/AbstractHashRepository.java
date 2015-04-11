package org.middleheaven.domain.repository;

import java.util.HashMap;
import java.util.Map;

import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.domain.query.EnumerableQuery;
import org.middleheaven.domain.query.QueryResult;
import org.middleheaven.util.identity.Identity;


public abstract class AbstractHashRepository<E> extends AbstractRepository<E> {

	private Map<Identity, E> instances = new HashMap<Identity, E>();
	
	@Override
	public QueryResult<E> findAll() {
		return new EnumerableQuery<E>(Enumerables.asEnumerable(instances.values()));
		
	}

	@Override
	public QueryResult<E> findByIdentity(final Identity id) {
		return new EnumerableQuery<E>(Enumerables.asEnumerable(instances.get(id)));
		
	}

	@Override
	public void remove(E instance) {
		instances.remove(this.getIdentityFor(instance));
	}

	protected void put(Identity id , E instance){
		instances.put(id, instance);
	}
}
