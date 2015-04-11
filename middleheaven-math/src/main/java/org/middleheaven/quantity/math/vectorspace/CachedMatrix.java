/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
 class CachedMatrix<F extends FieldElement<F, ?>> extends AbstractMatrix<F> {


	private int rows;
	private int columns;
	private Object[] cache;
	private CellResolver<F> resolver;

	public CachedMatrix (VectorSpace<F> provider, int rows, int columns, CellResolver<F> resolver){
		super(provider);
		this.rows = rows;
		this.columns = columns;
		this.cache = new Object[rows * columns];
		this.resolver = resolver;
	}
	

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final F get(int r, int c ){
		
		if (r >= this.rowsCount() || r < 0){
			throw new IndexOutOfBoundsException("Row Index " + r + ", Size: " + this.rowsCount());
		}
		
		if (c >= this.columnsCount() || c < 0){
			throw new IndexOutOfBoundsException("Column Index " + c + ", Size: " + this.columnsCount());
		}
		
		final Integer index = indexOf(r,c);
		Object value = cache[index]; 
		if (value == null){
			value = resolver.resolve(r, c);
			cache[index] = value;
		}
		
		return (F)value;
	}
	
	private Integer indexOf(int r, int c) {
		return Integer.valueOf(r * this.rowsCount() + c);
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

}
