/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import java.util.function.IntFunction;

import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
 class LazyProxyVector<F extends FieldElement<F, ?>> extends AbstractVector<F> {

	private final int size;
	private final Object[] cache;
	private IntFunction<F> getter;
	
	/**
	 * Constructor.
	 * @param provider
	 */
	public LazyProxyVector(VectorSpace<F> vectorSpace, int size , IntFunction<F> getter) {
		super(vectorSpace);
		this.size = size;
		this.cache = new Object[size];
		this.getter = getter;
		
	}


	@Override
	protected F boundSafeGet(int index) {
		if (index >= this.size() || index < 0){
			throw new IndexOutOfBoundsException("Index " + index + ", Size: " + this.size());
		}
		
		Object value = cache[index]; 
		if (value == null){

			value = getter.apply(index);
			cache[index] = value;
		}
		
		return (F) value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

}
