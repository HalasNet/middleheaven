/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public class SubMatrixRowsAndColumnsRemoved<F extends FieldElement<F,?>> extends AbstractMatrix<F> {

	
	
	private Matrix<F> original;
	private int removedRow;
	private int removedColumn;

	/**
	 * Constructor.
	 * @param vectorSpace
	 */
	public SubMatrixRowsAndColumnsRemoved(Matrix<F> original, int removedRow, int removedColumn) {
		super(original.getAlgebricStructure().getVectorSpace());
		this.original = original;
		this.removedRow = removedRow;
		this.removedColumn = removedColumn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public F get(int r, int c) {

			if ( r >= removedRow){
				r = r + 1;
			} 
			if ( c >= removedColumn){
				c = c + 1;
			} 

			return original.get(r, c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int rowsCount() {
		return original.rowsCount() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int columnsCount() {
		return original.columnsCount() - 1;
	}

	
}
