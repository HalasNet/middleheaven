/**
 * 
 */
package org.middleheaven.quantity.math;

import java.util.Random;

import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.MathStructuresFactory;

/**
 * The Field of Complexes.
 */
public final class ComplexAlgebricStructure implements Field<Complex, ComplexAlgebricStructure> {

	
	private static final ComplexAlgebricStructure ME = new ComplexAlgebricStructure();
	
	public static ComplexAlgebricStructure getInstance(){
		return ME;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex zero() {
		return MathStructuresFactory.getFactory().numberFor( Complex.class, "0", "0");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex one() {
		return MathStructuresFactory.getFactory().numberFor( Complex.class , "1", "0");
	}
	
	/**
	 * 
	 * @return the imaginary unit. The imaginary unit can be writen as a complex number 
	 * with no real part ( i = 0 + i.1)
	 */
	public Complex i(){
		return MathStructuresFactory.getFactory().numberFor( Complex.class , "0", "1");
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
	 * 
	 * Retruns a pure real complex number.
	 */
	@Override
	public Complex fromNumber(Number n) {
		return Complex.real(n);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex random() {
		return Complex.rectangular(Math.random(), Math.random());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex random(Random random) {
		return Complex.rectangular(random.nextDouble(), random.nextDouble());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex minus(Complex a, Complex b) {
		return a.minus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex plus(Complex a, Complex b) {
		return a.plus(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex multiply(Complex a, Complex b) {
		return a.times(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex inverse(Complex other) {
		return other.inverse();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex divide(Complex a, Complex b) {
		return a.over(b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Complex conjugate(Complex a) {
		return a.conjugate();
	}
}
