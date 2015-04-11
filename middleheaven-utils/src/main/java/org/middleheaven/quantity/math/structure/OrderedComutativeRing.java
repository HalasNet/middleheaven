/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.util.Comparator;

/**
 * 
 */
public interface OrderedComutativeRing<T extends OrdererComutativeRingElement<T, A>, A extends OrderedComutativeRing<T, A>> extends ComutativeRing<T, A>{

	
	public Comparator<T> getComparator();
}
