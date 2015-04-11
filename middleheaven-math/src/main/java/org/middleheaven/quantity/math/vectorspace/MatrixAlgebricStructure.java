/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;
import org.middleheaven.quantity.math.structure.Ring;

/**
 * 
 */
public class MatrixAlgebricStructure<F extends FieldElement<F, ?>> implements Ring<Matrix<F>, MatrixAlgebricStructure<F>>{


	private VectorSpace<F> space;
	private int columnsCount;
	private int rowsCount;

	/**
	 * Constructor.
	 * @param rowsCount
	 * @param columnsCount
	 */
	public MatrixAlgebricStructure(VectorSpace<F> space, int rowsCount, int columnsCount) {
	  this.space =space;
	  this.columnsCount = columnsCount;
	  this.rowsCount = rowsCount;
	}

	public VectorSpace<F> getVectorSpace(){
		return space;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> minus(Matrix<F> a, Matrix<F> b) {
		return a.minus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> zero() { 
		return new UniqueValueDiagonalMatrix<>(space, Math.max(rowsCount, columnsCount) , space.getField().zero());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> one() {
		return new UniqueValueDiagonalMatrix<>(space, Math.max(rowsCount, columnsCount) , space.getField().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> plus(Matrix<F> a, Matrix<F> b) {
		return a.plus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGroupAdditive() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRing() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isField() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> multiply(Matrix<F> a, Matrix<F> b) {
		return a.times(b);
	}

}
