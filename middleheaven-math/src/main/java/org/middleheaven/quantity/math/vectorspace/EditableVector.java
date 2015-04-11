/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public interface EditableVector<F extends FieldElement<F, ?>> extends Vector<F> {

	public EditableVector<F> set(int index, F value);
}
