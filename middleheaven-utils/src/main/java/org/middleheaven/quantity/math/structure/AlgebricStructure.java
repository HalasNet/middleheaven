/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface AlgebricStructure<T extends AlgebricStructureElement<T, A> , A extends AlgebricStructure<T,A> > {

	boolean isGroupAdditive();
	boolean isRing();
	boolean isField();
}
