/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface GroupIncrementableStructure <T extends GroupIncrementable<T,I,A> , I, A extends GroupIncrementableStructure<T,I,A> > extends IncrementableStructure<T, I, A>, DecrementableStructure<T, I, A> {

	
	public I zero();
}
