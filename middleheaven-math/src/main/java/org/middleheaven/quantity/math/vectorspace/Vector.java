/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;
import org.middleheaven.quantity.math.structure.GroupAdditiveElement;
import org.middleheaven.quantity.math.structure.ScalableElement;

/**
 * 
 */
public interface Vector <F extends FieldElement<F, ?>>  extends  GroupAdditiveElement<Vector<F>, VectorSpace<F>> , ScalableElement<F, Vector<F>>{
	
	public F get(int index);
	
	
	/**
	 * Shortcut for  Sum for all i of  this[i].times(other[i])
	 * 
	 * @param other
	 * @return
	 */
	public F pointwiseTimes(Vector<F> other);

	
	/**
	 * The number of dimensions on the vector.
	 * @return
	 */
	public int size();

}
