/**
 * 
 */
package org.middleheaven.quantity.math;

import java.util.Comparator;
import java.util.Random;

import org.middleheaven.collections.ComparableComparator;
import org.middleheaven.quantity.math.structure.MathStructuresFactory;
import org.middleheaven.quantity.math.structure.OrderedField;

/**
 * The Field of Reals
 */
public final class RealAlgebricStructure implements OrderedField<Real, RealAlgebricStructure> {

	private static final RealAlgebricStructure ME = new RealAlgebricStructure();
	
	private static final Real ZERO = MathStructuresFactory.getFactory().numberFor( Real.class , "0");
	private static final Real ONE = MathStructuresFactory.getFactory().numberFor( Real.class, "1");
	
	public static RealAlgebricStructure getInstance(){
		return ME;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public  Real zero() {
		return ZERO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real one() {
		return ONE;
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
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isField() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real fromNumber(Number n) {
		return Real.valueOf(n);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real random() {
		return Real.valueOf(Math.random());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real random(Random random) {
		return Real.valueOf(random.nextDouble());
	}

	@Override
	public Comparator<Real> getComparator() {
		return ComparableComparator.<Real>getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real inverse(Real x) {
		return x.inverse();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real minus(Real a, Real b) {
		return a.minus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real plus(Real a, Real b) {
		return a.plus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real multiply(Real a, Real b) {
		return a.times(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real divide(Real a, Real b) {
		return a.over(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real conjugate(Real a) {
		return a;
	}
	
	
}
