/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public class ZeroMatrix<F extends FieldElement<F,?> > implements Matrix<F> {

	
	private MatrixAlgebricStructure<F> structure;
	private int rows;
	private int columns;

	public ZeroMatrix(MatrixAlgebricStructure<F> structure, int rows, int columns){
		this.structure = structure;
		this.rows = rows;
		this.columns = columns;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> plus(Matrix<F> other) {
		return other;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> negate() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> minus(Matrix<F> other) {
		return other;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatrixAlgebricStructure<F> getAlgebricStructure() {
		return structure;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> times(Matrix<F> other) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> conjugate() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<F, ?> getField() {
		return this.structure.getVectorSpace().getField();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSquare() {
		return rows == columns;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSimmetric() {
		return isSquare();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> times(F scalar) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public F get(int r, int c) {
		return structure.getVectorSpace().getField().zero();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int rowsCount() {
		return rows;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int columnsCount() {
		return columns;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public F cofactor(int r, int c) {
		return structure.getVectorSpace().getField().zero();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasInverse() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> inverse() {
		throw new ArithmeticException("This matrix has no inverse");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> adjoint() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public F determinant() {
		return structure.getVectorSpace().getField().zero();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> getRow(int row) {
		return structure.getVectorSpace().subVector(columns, structure.getVectorSpace().getField().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> getColumn(int column) {
		return structure.getVectorSpace().subVector(rows, structure.getVectorSpace().getField().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> getDiagonal() {
		return structure.getVectorSpace().subVector(Math.min(rows, columns), structure.getVectorSpace().getField().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> rightTimes(Vector<F> vector) {
		return structure.getVectorSpace().subVector(rows, structure.getVectorSpace().getField().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> leftTimes(Vector<F> vector) {
		return structure.getVectorSpace().subVector(rows, structure.getVectorSpace().getField().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> transpose() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public F trace() {
		return  structure.getVectorSpace().getField().zero();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> remove(int row, int column) {
		return new ZeroMatrix<F>(structure, rows-1, columns-1);
	}

}
