/**
 * 
 */
package org.middleheaven.injection;

import org.middleheaven.reflection.ReflectedClass;

/**
 * 
 */
public class InstanceDefinition {

	private String name;
	private String scope;
	private InjectionModel model;
	private InstanceFactory instanceFactory;
	
	protected InstanceDefinition(String name, String scope, InjectionModel model, InstanceFactory instanceFactory) {
		super();
		this.name = name;
		this.scope = scope;
		this.model = model;
		this.instanceFactory = instanceFactory;
	}
	
	/**
	 * Obtains {@link InstanceFactory}.
	 * @return the instanceFactory
	 */
	public InstanceFactory getInstanceFactory() {
		return instanceFactory;
	}


	/**
	 * Obtains {@link String}.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtains {@link String}.
	 * @return the scope
	 */
	public String getScopeName() {
		return scope;
	}

	/**
	 * 
	 */
	public InjectionModel getInjectionModel() {
		return model;
	}

	
	
}
