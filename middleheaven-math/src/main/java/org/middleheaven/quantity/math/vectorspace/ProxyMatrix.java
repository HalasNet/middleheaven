/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public class ProxyMatrix<F extends FieldElement<F, ?>> extends AbstractMatrix<F> {


	private int rows;
	private int columns;
	private CellResolver<F> resolver;

	public ProxyMatrix (VectorSpace<F> provider, int rows, int columns , CellResolver<F> resolver){
		super(provider);
		this.rows = rows;
		this.columns = columns;
		this.resolver = resolver;
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
	public F get(int r, int c) {
		return resolver.resolve(r, c);
	}





}
