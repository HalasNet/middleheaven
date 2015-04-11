/**
 * 
 */
package org.middleheaven.instance;

/**
 * 
 */
public class InstanceUtils {

	
	public static <T> Instance<T> unCast(T obj){
		if (obj instanceof Instance){
			return (Instance<T>) obj;
		} else {
			throw new IllegalArgumentException("Object is not and instance proxy");
		}
	}
}
