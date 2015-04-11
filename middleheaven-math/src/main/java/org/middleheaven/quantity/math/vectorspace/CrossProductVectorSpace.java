/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public interface CrossProductVectorSpace<F extends FieldElement<F, ?>> extends VectorSpace<F> {

	
	Vector<F> crossProduct(Vector<F> a, Vector<F> b);
}
