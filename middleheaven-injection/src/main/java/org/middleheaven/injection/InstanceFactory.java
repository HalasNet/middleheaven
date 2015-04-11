/**
 * 
 */
package org.middleheaven.injection;


/**
 * 
 */
public interface InstanceFactory {
	
	public Object CreateInstance(InstanceDefinition definition, CreationContext creationContext, InstanceContainer container);
}
