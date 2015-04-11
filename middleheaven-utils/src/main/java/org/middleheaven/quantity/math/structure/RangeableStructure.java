/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface RangeableStructure<T extends Rangeable<T, I, A> , I , A extends RangeableStructure<T,I,A>> extends AlgebricStructure<T, A>  {

	
	public I until(T a , T b);
}
