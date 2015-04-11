/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface Rangeable <T extends Rangeable<T, I, A> , I, A extends RangeableStructure<T,I,A>> extends AlgebricStructureElement<T,A>{

	public I until(T other);
}
