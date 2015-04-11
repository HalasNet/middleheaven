package org.middleheaven.domain.repository;

import java.lang.reflect.ParameterizedType;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.middleheaven.domain.model.DomainModel;
import org.middleheaven.domain.query.EnumerableQuery;
import org.middleheaven.domain.query.QueryResult;

public abstract class AbstractRepository<E> implements Repository<E> {

	private Set<RepositoryListener> listeners = new CopyOnWriteArraySet<RepositoryListener> ();
	private DomainModel domainModel;

	public AbstractRepository(){
		
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClass () {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<E>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@Override
	public void addRepositoryListener(RepositoryListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeRepositoryListener(RepositoryListener listener) {
		listeners.remove(listener);
	}

	protected void fireChangeEvent(E instance, boolean isDeleted, boolean isAdded, boolean isUpdated){
		RepositoryChangeEvent<E> event = new RepositoryChangeEvent<E>(
				this,
				instance,
				isDeleted, isAdded, isUpdated
		);
		
		for (RepositoryListener listener : listeners){
			listener.onRepositoryChanged(event);	
		}

	}
	
	protected void fireAddedEvent(E instance){
		fireChangeEvent(instance,false,true,false);
	}
	
	protected void fireUpdatedEvent(E instance){
		fireChangeEvent(instance,false, false, true);
	}
	
	protected void fireRemoveEvent(E instance){
		fireChangeEvent(instance,true, false, false);
	}
	
	public QueryResult<E> findIdentical(E instance) {
		return findByIdentity(this.getIdentityFor(instance));
	}
	
	public QueryResult<E> findEquals(final E instance) {
		return new EnumerableQuery<E>(()->findAll().fetchAll().filter(it -> it.equals(instance)));
	}

	protected DomainModel getDomainModel(){
		return this.domainModel;
	}
}
