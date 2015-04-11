/**
 * 
 */
package org.middleheaven.injection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * 
 */
public class ConstructorCreationPoint implements CreationPoint {

	private Constructor<Object> constructor;
	private String[] references;
	
	public ConstructorCreationPoint(Constructor<Object> constructor, String[] references){
		this.constructor = constructor;
		this.references = references;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object CreateInstance(CreationContext creationContext,InstanceContainer container) {
		
		Object[] params = new Object[references.length];
		
		for (int i =0; i < references.length;i++){
			params[i] = container.getInstance(references[i], creationContext);
		}
		
		try {
			constructor.setAccessible(true);
			return constructor.newInstance(params);
		} catch (IllegalArgumentException e) {
			throw org.middleheaven.reflection.ReflectionException.manage(e);
		} catch (InstantiationException e) {
			throw org.middleheaven.reflection.ReflectionException.manage(e);
		} catch (IllegalAccessException e) {
			throw org.middleheaven.reflection.ReflectionException.manage(e);
		} catch (InvocationTargetException e) {
			throw org.middleheaven.reflection.ReflectionException.manage(e);
		}
	}

}
