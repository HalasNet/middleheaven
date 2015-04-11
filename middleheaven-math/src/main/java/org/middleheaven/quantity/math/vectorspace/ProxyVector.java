/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import java.util.function.IntFunction;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
class ProxyVector<F extends FieldElement<F, ? >> extends AbstractVector<F> {

	private final int size;
	private IntFunction<F> getter;

	/**
	 * Constructor.
	 * @param vectorSpace
	 */
	public ProxyVector(VectorSpace<F> vectorSpace, int size, IntFunction<F> getter) {
		super(vectorSpace);
		this.size = size;
		this.getter = getter;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected F boundSafeGet(int index) {
		return getter.apply(index);
	}


}
