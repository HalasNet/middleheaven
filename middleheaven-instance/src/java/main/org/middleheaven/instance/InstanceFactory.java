/**
 * 
 */
package org.middleheaven.instance;

/**
 * 
 */
public interface InstanceFactory {

	/**
	 * 
	 * @param type must be an interface
	 * @return
	 */
	public <T> Instance<T> CreateInstance(Class<T> type);
}
