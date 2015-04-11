/**
 * 
 */
package org.middleheaven.quantity.measure;

import org.middleheaven.quantity.math.structure.GroupAdditive;
import org.middleheaven.quantity.unit.Measurable;

/**
 * 
 */
public class AmountAlgebricStructure<M extends Amount<M,E> ,E extends Measurable> implements GroupAdditive<M, AmountAlgebricStructure<M,E>> {

	
	private M zero;

	public AmountAlgebricStructure(M zero){
		this.zero = zero;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public M zero() {
		return zero;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public M plus(M a, M b) {
		return a.plus(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public M minus(M a, M b) {
		return a.minus(b);
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



}
