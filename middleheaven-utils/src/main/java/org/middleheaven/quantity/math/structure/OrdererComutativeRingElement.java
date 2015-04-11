/**
 * 
 */
package org.middleheaven.quantity.math.structure;


/**
 * 
 */
public interface OrdererComutativeRingElement <T extends OrdererComutativeRingElement<T, A>, A extends OrderedComutativeRing<T, A>> 
	extends ComutativeRingElement<T, A>, Comparable<T>{

}
