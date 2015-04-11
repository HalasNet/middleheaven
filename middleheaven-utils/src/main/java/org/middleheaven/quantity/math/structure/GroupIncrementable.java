/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface GroupIncrementable<T extends GroupIncrementable<T,I,A> , I, A extends GroupIncrementableStructure<T, I, A>> extends Incrementable<T,I, A> , Decrementable<T, I, A> {

	
}
