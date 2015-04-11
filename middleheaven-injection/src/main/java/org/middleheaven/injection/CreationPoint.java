/**
 * 
 */
package org.middleheaven.injection;



/**
 * 
 */
public interface CreationPoint  {

	
	public Object CreateInstance(CreationContext creationContext, InstanceContainer container);

}
