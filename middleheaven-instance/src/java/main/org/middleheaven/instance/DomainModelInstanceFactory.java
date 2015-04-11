/**
 * 
 */
package org.middleheaven.instance;

import org.middleheaven.domain.model.DomainModel;
import org.middleheaven.domain.model.EntityModel;
import org.middleheaven.reflection.Reflector;

/**
 * 
 */
public class DomainModelInstanceFactory implements InstanceFactory{

	
	private DomainModel domainModel;

	public DomainModelInstanceFactory (DomainModel domainModel){
		this.domainModel = domainModel;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Instance<T> CreateInstance(Class<T> type) {
		EntityModel model = domainModel.getModelFor(type.getName());
		return (Instance<T>)Reflector.getReflector().reflect(type).newProxyInstance(new InstanceProxy(type,model),Instance.class);
	}

}
