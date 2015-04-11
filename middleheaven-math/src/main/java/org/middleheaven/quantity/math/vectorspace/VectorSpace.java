/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.FieldElement;
import org.middleheaven.quantity.math.structure.GroupAdditive;
import org.middleheaven.quantity.math.structure.ScalableSpace;

/**
 * 
 */
public interface VectorSpace<F extends FieldElement<F, ?>> extends GroupAdditive<Vector<F>,VectorSpace<F>> , ScalableSpace<Vector<F>, F, Vector<F>>{

	
	public EditableMatrix<F> squareMatrix();
	
	public EditableMatrix<F> subMatrix(int rowsCount, int columnsCount);
	
	public EditableMatrix<F> squareMatrix(F[] values);
	
	public EditableVector<F> vector(@SuppressWarnings("unchecked") F ... values);
	
	/**
	 * @return
	 */
	public EditableVector<F> vector();
	
	public EditableVector<F> vector(F value);
	
	public EditableVector<F> subVector(int dimentions, F value);
	
	/**
	 * @return
	 */
	public Field<F, ?> getField();
	
	public int dimentions();

	/**
	 * @return
	 */
	public Matrix<F> identityMatrix();


}
