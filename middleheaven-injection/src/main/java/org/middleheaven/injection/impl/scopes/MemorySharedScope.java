/**
 * 
 */
package org.middleheaven.injection.impl.scopes;

import java.util.HashMap;
import java.util.Map;

import org.middleheaven.injection.SharedScope;

/**
 * 
 */
public class MemorySharedScope implements SharedScope {

	private Map<String, Object> instances = new HashMap<String, Object>();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getInstance(String name) {
		return instances.get(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void offerInstance(String name, Object instance) {
		instances.put(name, instance);
	}
}
