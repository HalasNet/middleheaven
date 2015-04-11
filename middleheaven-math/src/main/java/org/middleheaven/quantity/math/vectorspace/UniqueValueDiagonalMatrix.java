/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public class UniqueValueDiagonalMatrix<F extends FieldElement<F, ?>> extends AbstractMatrix<F> {

	
	private F zero;
	private F value;
	private int size;
	
	public UniqueValueDiagonalMatrix(VectorSpace<F> vectorSpace,  int size, F value){
		super(vectorSpace);
		this.value = value;
		this.zero = getField().zero();
		this.size = size; // rows = colums = size;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> negate() {
		return new UniqueValueDiagonalMatrix<>(this.getVectorSpace(), size, value.negate());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> conjugate() {
		return new UniqueValueDiagonalMatrix<>(this.getVectorSpace(), size, this.getField().conjugate(value));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> times(F scalar) {
		return new UniqueValueDiagonalMatrix<>(this.getVectorSpace(), size, value.times(scalar));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return value.isZero();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatrixAlgebricStructure<F> getAlgebricStructure() {
		return new MatrixAlgebricStructure<F>(this.getVectorSpace(), size, size);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		return this.value.isOne();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> times(Matrix<F> other) {
		if (value.isZero()){
			return new ZeroMatrix<F>(this.getAlgebricStructure(), this.rowsCount(), this.columnsCount());
		} else if (value.isOne()){
			return other;
		}
		EditableMatrix<F> result = this.getAlgebricStructure().getVectorSpace().squareMatrix();
		
		for (int i = 0; i < this.getAlgebricStructure().getVectorSpace().dimentions(); i++){
			result.set(i,i, other.get(i, i).times(value));
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSquare() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSimmetric() {
		return true;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public F get(int r, int c) {
		return r== c ? value : zero;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int rowsCount() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int columnsCount() {
		return size;
	}

//	/**
//	 * {@inheritDoc} // TODO implement 
//	 */
//	@Override
//	public F cofactor(int r, int c) {
//		throw new UnsupportedOperationException("Not implememented yet");
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasInverse() {
		return !value.isZero();
	}

//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Matrix<F> adjoint() {
//		// TODO 
//		throw new UnsupportedOperationException("Not implememented yet");
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public F determinant() {
		if (value.isZero()){
			return value;
		}
		F val = value;
		for (int i = 0 ; i < size ; i++){
			val = val.times(value);
		}
		return val;
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Vector<F> getRow(int row) {
//		throw new UnsupportedOperationException("Not implememented yet");
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Vector<F> getColumn(int column) {
//		throw new UnsupportedOperationException("Not implememented yet");
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Vector<F> getDiagonal() {
//		throw new UnsupportedOperationException("Not implememented yet");
//	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> transpose() {
		return this;
	}
}
