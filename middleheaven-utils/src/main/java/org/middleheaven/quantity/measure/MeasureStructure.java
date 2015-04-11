/**
 * 
 */
package org.middleheaven.quantity.measure;

import org.middleheaven.quantity.math.structure.GroupAdditive;
import org.middleheaven.quantity.unit.Measurable;

/**
 * 
 */
public class MeasureStructure<E extends Measurable> 
implements GroupAdditive<Measure<E>, MeasureStructure<E>> {

	
	private Measure<E> zero;

	public MeasureStructure (Measure<E> zero){
		this.zero = zero;
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
	public Measure<E> zero() {
		return zero;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Measure<E> minus(Measure<E> a, Measure<E> b) {
		return a.minus(b);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Measure<E> plus(Measure<E> a, Measure<E> b) {
		return a.plus(b);
	}

}
