package org.middleheaven.quantity.math;

import org.middleheaven.collections.progression.Progression;
import org.middleheaven.collections.progression.StepLinearProgression;
import org.middleheaven.quantity.math.structure.MathStructuresFactory;
import org.middleheaven.quantity.math.structure.OrderedFieldElement;
import org.middleheaven.util.StepIncrementable;


/**
 * Represents an element of |R (the real numbers set) 
 * 
 */
public abstract class Real extends Numeral<Real, RealAlgebricStructure> implements OrderedFieldElement<Real , RealAlgebricStructure>, Comparable<Numeral<? super Real , RealAlgebricStructure> > , StepIncrementable <Real, Real> {

	private static final long serialVersionUID = 5613604361361447882L;
	protected static final int SCALE = 22;
	
	/**
	 * Returns a real defined by a fraction of to integers.
	 * @param num
	 * @param den
	 * @return
	 */
	public static Rational rational (int num , int den){
		return MathStructuresFactory.getFactory().numberFor(Rational.class, num, den);
	}
	
	/**
	 * Returns an array of {@link Real}s based on an array of {@link Number}.
	 * @param array
	 * @return
	 */
	public static Real[] valueOf(java.lang.Number ... array){
		MathStructuresFactory factory = MathStructuresFactory.getFactory();
		Real[] res = new Real[array.length];
		for (int i =0 ; i < array.length; i++){
			res[i] = factory.numberFor(Real.class, array[i].toString());
		}
		return res;
	}

	/**
	 * Returns a Real based on a non-formated string.
	 * @param value the value.
	 * @return the corresponding {@link Real}.
	 */
	public static Real valueOf (String value) {
		return MathStructuresFactory.getFactory().numberFor( Real.class, value);
	}

	/**
	 * Returns a Real based on a {@link Numeral}.
	 * @param value the value.
	 * @return the corresponding {@link Real}.
	 */
	public static Real valueOf (Numeral<?, ?> other) {
		if (Real.class.isInstance(other)){
			return Real.class.cast(other);
		} 
		return MathStructuresFactory.getFactory().numberFor(Real.class, other.toString());
	}

	/**
	 * Obtains a {@link Real} than best represents the ratio between the <code>numerator</code> and <code>denominator</code>.
	 * 
	 * @param numerator
	 * @param denominator
	 * @return a {@link Real} than best represents the ratio between the <code>numerator</code> and <code>denominator</code>
	 */
	public static Real valueOf (Numeral<?, ?> numerator , Numeral<?, ? > denominator) {
		return MathStructuresFactory.getFactory().numberFor(Real.class, valueOf(numerator), valueOf(denominator));
	}
	
	/**
	 * Returns a Real based on a {@link Number}.
	 * @param value the value.
	 * @return the corresponding {@link Real}.
	 */
	public static Real valueOf (java.lang.Number other) {
		return MathStructuresFactory.getFactory().numberFor(Real.class,other);
	}
	
	/**
	 * Obtains a {@link Real} than best represents the ratio between the <code>numerator</code> and <code>denominator</code>.
	 * 
	 * @param numerator
	 * @param denominator
	 * @return a {@link Real} than best represents the ratio between the <code>numerator</code> and <code>denominator</code>
	 */
	public static Real valueOf (java.lang.Number numerator , java.lang.Number denominator) {
		return MathStructuresFactory.getFactory().numberFor(Real.class, valueOf(numerator), valueOf(denominator));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Real toReal() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Complex toComplex() {
		return Complex.rectangular(this, this.getAlgebricStructure().zero());
	}

	
	@Override
	public Real minus(Number n) {
		return this.minus(Real.valueOf(n));
	}

	@Override
	public Real plus(Number n) {
		return this.plus(Real.valueOf(n));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real minus(Real other) {
		return this.plus(other.negate());
	}
	
	public Real over(Number n) {
		return this.over(Real.valueOf(n));
	}

	public Real times(Number n) {
		return this.times(Real.valueOf(n));
	}
	
	protected final int rank(){
		return 1;
	}

	
	public Real min(Real other) {
		return this.compareTo(other) > 0 ? other : this;
	}

	public Real max(Real other) {
		return this.compareTo(other) > 0 ? this : other;
	}
	
	
	public Progression<Real, Real> upTo(Real other){
		return new StepLinearProgression<Real, Real>(this, other,this.getAlgebricStructure().one());
	}
	
	public Real arctan() {
		return Real.valueOf(BigMath.arctan(this.asNumber(), SCALE));
	}

	public Real sqrt() {
		return Real.valueOf(BigMath.sqrt(this.asNumber() , SCALE));
	}

	public Real cos() {
		return Real.valueOf(BigMath.cos(this.asNumber(), SCALE));
	}

	public Real sin() {
		return Real.valueOf(BigMath.sin(this.asNumber(), SCALE));
	}
	
	public Real exp() {
		return Real.valueOf(BigMath.exp(this.asNumber(), SCALE));
	}

	public Real ln() {
		return Real.valueOf(BigMath.ln(this.asNumber(), SCALE));
	}

	public Real tan() {
		return sin().over(cos());
	}


	/**
	 * Returns the exponent power od this.
	 * @param exponent
	 * @return this value raised to the powr of the exponent.
	 */
	public Real raise(int exponent) {
		if (exponent==0){
			return this.getAlgebricStructure().one();
		} else if (exponent>0){
			 Real a = this;
			 Real s = this;
			 for (int i = 1 ; i < exponent ; i++){
				 s = s.times(a);
			 }
			 return s;
		} else {
			 Real a = this;
			 Real s = this;
			 for (int i = 0 ; i <= -exponent ; i++){
				 s = s.over(a);
			 }
			 return s;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RealAlgebricStructure getAlgebricStructure() {
		return RealAlgebricStructure.getInstance();
	}
	
	public abstract boolean isWhole();
}
