/**
 * 
 */
package org.middleheaven.injection;



/**
 * 
 */
public class InstanciateFactory implements InstanceFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object CreateInstance(InstanceDefinition definition, CreationContext creationContext, InstanceContainer container){
		
		return definition.getInjectionModel().getCreationPoint().CreateInstance(creationContext, container);
	}

}
