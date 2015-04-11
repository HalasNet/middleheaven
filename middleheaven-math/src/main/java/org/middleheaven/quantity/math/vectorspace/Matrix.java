/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.Conjugatable;
import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.FieldElement;
import org.middleheaven.quantity.math.structure.RingElement;

/**
 * 
 */
public interface Matrix<F extends FieldElement<F, ?>> extends RingElement<Matrix<F>, MatrixAlgebricStructure<F>>, Conjugatable<Matrix<F>>{


	/**
	 * @return the underlying field being used for the elements of the matrix.
	 */
	public Field<F, ?> getField();

	/**
	 * 
	 * @return <code>true</code> if this matrix is square, i.e. the number of rows equals the number of columns.
	 */
	public boolean isSquare();
	
	/**
	 * 
	 * @return <code>true</code> if matrix is square and matrix.get(i,j).equals(matrix.get(j,i) for all i and j.
	 */
	public boolean isSimmetric();
	
	public Matrix<F> times(F scalar);
	
	/**
	 * Obtain the value at position (r,c)
	 * @param r the row index. 0 is the first.
	 * @param c the column index. 0 is the first.
	 * @return
	 */
	public F get(int r, int c);
	
	/**
	 * sets the value at position (r,c)
	 * @param r
	 * @param c
	 * @param value
	 * @return
	 */
	//public abstract Matrix<F> set(int r, int c, F value);
	
	/**
	 * 
	 * @return the number of rows
	 */
	public int rowsCount();
	
	/**
	 * 
	 * @return the number of columns
	 */
	public int columnsCount();
	
	/**
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public F cofactor(int r, int c);
	
	/**
	 * 
	 * @return <code>true</code> if this matrix has an inverse.
	 */
	public boolean hasInverse();

	/**
	 * 
	 * @return the inverse matrix.
	 * @throws ArithmeticException if no inverse exists.
	 */
	public Matrix<F> inverse();
	
	/**
	 * 
	 * @return this matrix adjoint matrix
	 */
	public Matrix<F> adjoint();

	/**
	 * 
	 * @return this matrix determinant
	 */
	public F determinant();

	/**
	 * Retrives a row as a vector.
	 * @param row the index of the row to retrive.
	 * @return a Vector<F> representing the values in the <code>row</code> row.
	 */
	public Vector<F> getRow(int row);

	/**
	 * Retrives a column as a vector.
	 * @param column the index of the column to retrive.
	 * @return a Vector<F> representing the values in the <code>column</code> column.
	 */
	public Vector<F> getColumn(int column);

	public Vector<F> getDiagonal();

	/**
	 * Calculates y[i] = A[i,j] * x[j];
	 * 
	 * @param vector
	 * @return
	 */
	public Vector<F> rightTimes (Vector<F> vector);

	
	/**
	 * Calculates y[i] =  x[j] * A[j,i];
	 * 
	 * @param vector
	 * @return
	 */
	public Vector<F> leftTimes (Vector<F> vector);

	/**
	 * 
	 * @return this matrix transpose.
	 */
	public Matrix<F> transpose();
	
	/**
	 * 
	 * @return this matrix trace
	 */
	public F trace();

	/**
	 * Return a matrix where the r row and the c column have bean removed
	 * @param row index of the row to remove
	 * @param column index of the column to remove.
	 * @return
	 */
	public Matrix<F> remove(final int row, final int column);


}
