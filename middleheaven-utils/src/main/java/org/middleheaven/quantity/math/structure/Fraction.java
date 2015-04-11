package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.middleheaven.quantity.math.BigMath;
import org.middleheaven.quantity.math.Int;
import org.middleheaven.quantity.math.Numeral;
import org.middleheaven.quantity.math.Rational;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.RealAlgebricStructure;

/**
 * 
 */
class Fraction extends Rational{

	private static final long serialVersionUID = 1L;

	private final static Fraction ZERO  = new Fraction(Int.ZERO , Int.ONE);
	private final static Fraction ONE  = new Fraction(Int.ONE , Int.ONE);

	// Ratio pattern like implementation to adjourn the 
	// division as much as possible.
	private Int numerator; 
	private Int denominator; 

    protected static Fraction valueFrom (String value){
		return valueFrom(new BigDecimal(value));
	}
	
     private static Fraction valueFrom (BigDecimal value){
    	
    	if (value.signum() == 0){
    		// zero
    		return ZERO;
    	} else if (value.scale() <= 0 || value.stripTrailingZeros().scale() <= 0){
    		// integer value
    		return new Fraction(Int.valueOf(value.toBigIntegerExact()), Int.ONE);
    	} else {
    		final BigDecimal scale = BigMath.intPower(BigDecimal.TEN, value.scale(), 0);
    		
    		final BigInteger numerator = value.multiply(scale).toBigInteger();
    		final BigInteger denominator = scale.toBigInteger();

    		if (numerator.compareTo(BigInteger.ZERO) == 0){
    			return ZERO;
    		} else if (numerator.compareTo(denominator) == 0){
    			return ONE;
    		}
    		
    		BigInteger gcd = BigMath.gcd (numerator, denominator);

    		if (gcd.signum() ==0 ){
    			return new Fraction(Int.valueOf(numerator), Int.valueOf(denominator));
    		} else {
    			return new Fraction(Int.valueOf(numerator.divide(gcd)), Int.valueOf(denominator.divide(gcd)));
    		}
    		
    	}	
	}
	
    protected static Fraction reduce (Int numerator,Int denominator){
    	Int gcd = Int.gcd (numerator, denominator);

		if (gcd.signum() == 0) {
			return new Fraction(numerator, denominator);
		} else {
			return new Fraction(numerator.divide(gcd), denominator.divide(gcd));
		}
    }
    
	Fraction (Int numerator,Int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}

	Fraction (String value){
		Fraction f = valueFrom(new BigDecimal(value));
		this.numerator = f.numerator;
		this.denominator = f.denominator;
	}
	
	public BigDecimal asNumber() {
		return numerator.signum()==0 
				? BigDecimal.ZERO 
				: (denominator.compareTo(BigInteger.ONE) == 0
					? numerator.asNumber()
					: numerator.asNumber().divide(denominator.asNumber(), SCALE, RoundingMode.HALF_EVEN));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWhole() {
		return this.numerator.signum() == 0 || this.numerator.divideAndRemainder(denominator)[1].compareTo(BigInteger.ONE) == 0;
	}

	@Override
	public final boolean isZero() {
		return numerator.signum()==0;
	}

	@Override
	public Rational inverse() {
		if (numerator.signum() == 0){
			throw new ArithmeticException("Can not invert zero");
		}
		return new Fraction(denominator, numerator);
	}

	@Override
	public Rational negate() {
		if (denominator.signum()>0){
			// negate numerator
			return new Fraction(numerator.negate(), denominator);
		} else {
			// negate denominator
			return new Fraction(numerator, denominator.negate());
		}
	}

	@Override
	public Real plus(Real other) {
		Fraction real;
		if (!(other instanceof Fraction)){
			real = valueFrom(other.asNumber());
		} else {
		    real = (Fraction)other;
		}
		return plus (real.numerator , real.denominator);
	}

	private Fraction plus(Int otherNumerator, Int otherDenominator){
		Int[] multipliers = this.multipliers(this.denominator,otherDenominator );

		return Fraction.reduce(
				this.numerator.times(multipliers[0]).plus(otherNumerator.times(multipliers[1])),
				this.denominator.times(multipliers[0])
		).simplify();
	}

	private Fraction simplify() {
		if (numerator.signum()==0 || denominator.compareTo(BigInteger.ONE) == 0){
			return this; // is zero or divided by 1
		}

		Int gcd = Int.gcd ( numerator, denominator);

		if (gcd.signum()==0){
			return this;
		}

		return new Fraction(numerator.divide(gcd),denominator.divide(gcd));

	}

	
	private Int[] multipliers (Int d1 , Int d2){
		int compare = d1.compareTo(d2);
		Int[] division;
		if ( compare==0){
			return new Int[]{Int.ONE,Int.ONE};
		} else if ( compare <0 ){
			division = d2.divideAndRemainder(d1);
			if (division[1].signum()==0){
				return new Int[]{division[0],Int.ONE};
			} 
		} else {
			division = d1.divideAndRemainder(d2);
			if (division[1].signum()==0){
				return new Int[]{Int.ONE,division[0]};
			} 
		}
		return new Int[]{d2,d1};
	}


	@Override
	public Real times(Real other) {
		Fraction f;
		if (!(other instanceof Fraction)){
			f = valueFrom(other.asNumber());
		} else {
			f = (Fraction)other;
		}
		
		return times(f);
	}


	@Override
	public Real over(Real other) {
		
		if (other.isZero()){
			throw new ArithmeticException("Division by zero");
		}
		
		Fraction f;
		if (!(other instanceof Fraction)){
			f = valueFrom(other.asNumber());
		} else {
			f = (Fraction)other;
		}
		
		return over(f);

	}


	@Override
	public String toString() {
		return this.denominator.compareTo(BigInteger.ONE) == 0 ? this.numerator.toString() : this.numerator.toString() + "/" + this.denominator.toString();
	}

	public Real nextBy(Real increment) {
		return this.plus(increment);
	}

	public Real previousBy(Real increment) {
		return this.minus(increment);
	}
	

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equalsSame(Real other) {
		return compareToSame((Fraction)other)==0;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode(){
		return numerator.hashCode() ^ denominator.hashCode();
	}

	@Override
	public int compareTo(Real other) {
		if (other instanceof Fraction){
			return this.compareToSame((Fraction)other);
		}
		return this.asNumber().compareTo(other.asNumber());
	}

	private int compareToSame(Fraction other){

		Int denominatorProduct = denominator.times(other.numerator);
		Int numeratorProduct = numerator.times(other.denominator);

		return numeratorProduct.compareTo(denominatorProduct);

	}

	@Override
	public Int toInt() {
		if (!this.isWhole()){
			throw new ArithmeticException("Fraction " + this.toString() + " does not represent an whole number");
		}
		return Int.valueOf(this.asNumber());
	}

	@Override
	public Rational abs() {
		return new Fraction(this.numerator.abs(), this.denominator.abs());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int signum() {
		return this.numerator.signum() * this.denominator.signum();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		return numerator.equals(denominator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Numeral<? super Real, RealAlgebricStructure> other) {
		if (other instanceof Fraction){
			return this.compareToSame((Fraction)other);
		}
		return this.asNumber().compareTo(other.asNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int floor() {
		return numerator.divideAndRemainder(this.denominator)[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int ceil() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int numerator() {
		return numerator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int denominator() {
		return denominator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Rational ratioOf(Int numerator, Int denominator) {
		if (numerator.isZero()){
			return Fraction.ZERO;
		} else {
			return reduce(numerator, denominator);
		}
	}




}
