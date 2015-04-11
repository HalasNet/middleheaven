/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public interface EditableMatrix<F extends FieldElement<F, ?>> extends Matrix<F> {

	public EditableMatrix<F> set(int r, int c , F value);
}
