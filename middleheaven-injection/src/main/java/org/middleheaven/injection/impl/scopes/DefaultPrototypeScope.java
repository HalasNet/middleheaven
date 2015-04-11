/**
 * 
 */
package org.middleheaven.injection.impl.scopes;

import org.middleheaven.injection.PrototypeScope;

/**
 * 
 */
public class DefaultPrototypeScope implements PrototypeScope{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getInstance(String name) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void offerInstance(String name, Object instance) {
		//no-op
	}

	

}
