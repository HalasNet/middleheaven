package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.DefaultMatrixInvertion;
import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * Simple base implementation for a {@link Matrix}.
 * @param <F> the {@link FieldElement} type of the elements of the matrix.
 */
public abstract class AbstractMatrix<F extends FieldElement<F, ?>> implements Matrix<F>{



	private VectorSpace<F> vectorSpace;

	public AbstractMatrix(VectorSpace<F> vectorSpace) {
		this.vectorSpace = vectorSpace;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatrixAlgebricStructure<F> getAlgebricStructure() {
		return new MatrixAlgebricStructure<F>(this.getVectorSpace(),this.rowsCount(), this.columnsCount());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		if (this.rowsCount() != this.columnsCount()){
			return false;
		}
		for (int i = 0; i < this.rowsCount(); i++){
			if (!get(i,i).isOne()){
				return false;
			}
		}
		for (int i = 0; i < this.rowsCount(); i++){
			for (int j = 0; j < this.columnsCount(); j++){
				if (i != j && !get(i,j).isZero()){
					return false;
				}
			}	
		}
		return true;
	}

	protected final VectorSpace<F> getVectorSpace(){
		return vectorSpace;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public Field<F, ?> getField(){
		return vectorSpace.getField();
	}

	public boolean isSquare(){
		return this.rowsCount() == this.columnsCount();
	}

	public boolean isSimmetric(){
		if (this.rowsCount()==1){
			return true;
		} else if (!this.isSquare()){
			return false;
		} 

		return this.equalsOther(this.transpose());
	}


	public Vector<F> getRow(final int row){

		return new ProxyVector<F>(this.vectorSpace, this.columnsCount() , index -> get(row,index));

	}

	public Vector<F> getColumn(final int column){

		return new ProxyVector<F>(this.vectorSpace, this.rowsCount(), index -> get(index, column));

	}

	public Vector<F> getDiagonal(){

		return new ProxyVector<F>(this.vectorSpace, this.rowsCount(), index -> get(index, index));

	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasInverse() {
		return this.isSquare() && !this.determinant().isZero();
	}

	/**
	 * 
	 * @return the inverse matrix.
	 */
	public Matrix<F> inverse() {

		if (hasInverse()){
			return new DefaultMatrixInvertion().invert(this);
		} else {
			throw new ArithmeticException("This matrix has no inverse");
		}

	}

	@Override
	public final Matrix<F> minus(final Matrix<F> other) {
		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				(r, c) ->  AbstractMatrix.this.get(r, c).minus(other.get(r, c))
				);

	}

	@Override
	public Matrix<F> negate() {
		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				(r, c) ->  AbstractMatrix.this.get(r, c).negate()
				);
	}

	@Override
	public  Matrix<F> plus(final Matrix<F> other) {
		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				(r, c) ->  get(r, c).plus(other.get(r, c))
				);

	}

	public Matrix<F> times(final F a) {
		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				(r, c) ->  get(r, c).times(a)
				);

	}

	@Override
	public Matrix<F> adjoint() {
		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				AbstractMatrix.this::cofactor
				);

	}


	@Override
	public Matrix<F> conjugate() {

		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				(r, c) ->  getField().conjugate(get(r, c))
				);

	}


	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> rightTimes(final Vector<F> vector) {
		return new ProxyVector<F>(this.vectorSpace, this.columnsCount(), index -> getColumn(index).pointwiseTimes(vector));
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> leftTimes(final Vector<F> vector) {
		return new ProxyVector<F>(this.vectorSpace, this.columnsCount(), index -> getRow(index).pointwiseTimes(vector));
	}

	@Override
	public Matrix<F> times(final Matrix<F> other) {

		return new CachedMatrix<F>(
				vectorSpace, rowsCount(), columnsCount(),
				(r, c) -> {
					// the value for r, c is the linear combination of the original row, with the other column
					// T = A * B
					// T[r,c] = A[r,i] * B[i,c]

					return getRow(r).pointwiseTimes(other.getColumn(c));
				}
				);
	}


	@Override
	public F determinant() {
		return determinant(this);
	}


	private F determinant(Matrix<F> mat) {

		if (!mat.isSquare()){
			throw new ArithmeticException("Matriz is not square");
		}

		if(mat.rowsCount() == 1) {
			return mat.get(0,0);
		}

		if(mat.rowsCount() == 2) { 
			return mat.get(0,0).times(mat.get(1,1)).minus(mat.get(0,1).times(mat.get(1,0))); 
		}

		F zero = this.getField().zero();

		F result = zero;
		Vector<F> masterRow =  mat.getRow(0);

		for(int i = 0; i < this.columnsCount(); i++) { 
			Matrix<F> temp = mat.remove(0,i);

			F x = masterRow.get(i);

			if( Math.pow(-1, i) == -1){
				result = result.plus(determinant(temp).times(x.negate())); 
			} else {
				result = result.plus(determinant(temp).times(x)); 
			}

		}
		return result; 

	} 

	/**
	 * Return a matrix where the r row and the c column have bean removed
	 * @param row index of the row to remove
	 * @param column index of the column to remove.
	 * @return
	 */
	public Matrix<F> remove(final int row, final int column) {

		return new SubMatrixRowsAndColumnsRemoved<>(this, row, column);
			
	}

	@Override
	public final F cofactor(int r, int c) {
		// the determinant of the matriz by removing this row and columns
		if( Math.pow(-1, r+c)==-1){
			return this.remove(r, c).determinant().negate();
		} else {
			return this.remove(r, c).determinant(); 
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public Matrix<F> transpose() {
		return new TransposedMatrix<F>(this, this.vectorSpace);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final F trace(){
		F result = this.get(0, 0);
		for (int i=1; i<this.rowsCount();i++){
			result = result.plus(this.get(i,i));
		}
		return result;
	}


	/**
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other){
		return other instanceof Matrix && equalsOther((Matrix<F>)other); 
	}

	private boolean equalsOther(Matrix<?> other){
		if (this.rowsCount()!= other.rowsCount() || this.columnsCount() != this.columnsCount()){
			return false;
		}

		for (int r =0; r < this.rowsCount(); r++){
			for (int c =0; c < this.columnsCount(); c++){
				if (!this.get(r, c).equals(other.get(r, c))){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public int hashCode(){
		return this.columnsCount() + 13 * this.rowsCount();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public String toString(){
		StringBuilder text = new StringBuilder("\n");
		for (int r =0; r < this.rowsCount(); r++){
			for (int c =0; c < this.columnsCount(); c++){
				text.append(this.get(r, c).toString()).append(",");
			}
			text.delete(text.length()-1,text.length());
			text.append("\n");
		}
		return text.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return this.equals(this.getAlgebricStructure().zero());
	}

}
