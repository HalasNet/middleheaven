/**
 * 
 */
package org.middleheaven.injection;

/**
 * 
 */
public interface Scope {

	/**
	 * Obtains the instance with the given name from the scope.
	 * @param name
	 * @return
	 */
	Object getInstance(String name);

	/**
	 * Recives instance after creation.
	 * @param instance
	 */
	void offerInstance(String name, Object instance);

}
