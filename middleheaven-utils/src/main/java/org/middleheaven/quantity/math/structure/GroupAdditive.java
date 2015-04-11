/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface GroupAdditive<T extends GroupAdditiveElement<T, A> , A extends GroupAdditive<T, A>> extends MonoidAdditive<T, A> , DecrementableStructure<T, T, A> {

	/**
	 * 
	 * @return the aditive identity element.
	 */
	public T minus(T a , T b);
	

}
