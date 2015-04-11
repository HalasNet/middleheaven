/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public interface Sink<T> {

	public static StringsSinkFactory ofStrings(){
		return new StringsSinkFactory();
	}

	/**
	 * @return
	 */
	public static IntegersSinkFactory ofIntegers() {
		return new IntegersSinkFactory();
	}
	
	/**
	 * @return
	 */
	public static <T> CollectionsSinkFactory<T> collections() {
		return new CollectionsSinkFactory<T>();
	}


	
}
