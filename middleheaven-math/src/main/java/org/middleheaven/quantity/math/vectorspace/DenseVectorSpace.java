/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import java.util.Random;

import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public class DenseVectorSpace<F extends FieldElement<F, A> , A extends Field<F,A>> implements VectorSpace<F> {

	
	public static <D extends FieldElement<D, G>, G extends Field<D, G>> VectorSpace<D> getVectorSpaceOver(G field, int dimensions) {
		return dimensions == 3 ? DenseVector3DSpace.getVectorSpaceOver(field) : new DenseVectorSpace<D, G>(dimensions, field);
	}
	
	
	private Field<F, A> field;
	private int dimentions;

	DenseVectorSpace (int dimensions, Field<F, A> field){
		this.field = field;
		this.dimentions = dimensions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<F, A> getField() {
		return field;
	}

	public Matrix<F> random(int rows, int columns){
		return random(rows,columns,2);
	}

	public Matrix<F> random(int rows, int columns , long seed){
		return random(rows,columns,new Random(seed));
	}

	public Matrix<F> random(int rows, int columns , Random r){

		int length = rows*columns;

		DenseMatrix<F> result = new DenseMatrix<F>(this, rows, columns, field.one());

		for (int i=0; i < length; i++){
			result.setByIndex(i, field.random(r));
		}

		return result;

	}

	public Matrix<F> identity(int size) {
		return diagonal(size, field.one());
	}

	public Matrix<F> diagonal(int size, final F value) {
		return new ProxyMatrix<F>(this, size, size , (r, c) -> r == c ? value : value.getAlgebricStructure().zero());
	}


	public  Matrix<F> matrix(Vector<F>... rows){

		if (rows.length == 0){
			throw new IllegalArgumentException("rows cannot be empty");
		}

		int columns = rows[0].size();

		DenseMatrix<F> result = new DenseMatrix<F>(this,rows.length, columns, field.zero());

		for (int r=0; r < rows.length; r++){

			Vector<F> vector = rows[r];

			if (vector.size() != columns){
				throw new IllegalArgumentException("rows do not have the same size");
			}

			for (int c = 0; c < columns; c++){
				result.set(r,c , vector.get(c));
			}
		}


		return result;

	}

	public Matrix<F> matrix(int rows, int columns, F ... values){
		if (values.length != rows * columns){
			throw new IllegalArgumentException("Invalid dimensions");
		}

		DenseMatrix<F> result = new DenseMatrix<F>(this,rows, columns, field.zero());

		for (int i=0; i < values.length; i++){
			result.setByIndex(i, values[i]);
		}


		return result;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> minus(Vector<F> a, Vector<F> b) {
		return a.minus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> zero() {
		return new DenseVector<F>(this , dimentions, field.zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> plus(Vector<F> a, Vector<F> b) {
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
		return false;
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
	public EditableMatrix<F> squareMatrix() {
		return new DenseMatrix<F>(this, dimentions, dimentions, field.zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditableMatrix<F> squareMatrix(F[] values) {
		return new DenseMatrix<F>(this, dimentions, dimentions, values);

	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditableMatrix<F> subMatrix(int rowsCount, int columnsCount) {
		if (rowsCount > dimentions){
			throw new IllegalArgumentException("Number of rows cannot exceed vector space dimention");
		}
		if (columnsCount > dimentions){
			throw new IllegalArgumentException("Number of columns cannot exceed vector space dimention");
		}
		return new DenseMatrix<F>(this, rowsCount, columnsCount, field.zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditableVector<F> vector() {
		return new DenseVector<F>(this, dimentions, field.zero());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditableVector<F> vector(F value) {
		return new DenseVector<F>(this , dimentions, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditableVector<F> vector(F... values) {
		if (values.length > dimentions){
			throw new IllegalArgumentException("Number of elements cannot exceed vector space dimention");
		}
		return new DenseVector<F>(this, values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditableVector<F> subVector(int dimentions, F value) {
		if (dimentions > this.dimentions){
			throw new IllegalArgumentException("Number of elements cannot exceed vector space dimention");
		}
		return new DenseVector<F>(this , dimentions, value);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix<F> identityMatrix() {
		return new UniqueValueDiagonalMatrix<>(this, dimentions, this.field.one());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int dimentions() {
		return dimentions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> times(Vector<F> element, F scalar) {
		return element.times(scalar);
	}

}
