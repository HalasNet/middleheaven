package org.middleheaven.quantity.math;

import java.math.BigInteger;

import org.middleheaven.collections.progression.LinearProgression;
import org.middleheaven.collections.progression.Progressable;
import org.middleheaven.collections.progression.Progression;
import org.middleheaven.quantity.math.structure.BigInt;
import org.middleheaven.quantity.math.structure.MathStructuresFactory;
import org.middleheaven.quantity.math.structure.OrdererComutativeRingElement;
import org.middleheaven.util.SelfIncrementable;


/**
 * Represents an integer number.
 * The range depends on the bytes used. Int will scale between ranges automaticlly when an overflow is encontered, ultimatly behaving as a BigInteger
 * 
 * 
 */
public abstract class Int extends Numeral<Int, IntAlgebricStructure> implements OrdererComutativeRingElement<Int,IntAlgebricStructure> , Progressable<Int>, SelfIncrementable <Int>{

	private static final long serialVersionUID = 8636156681654308959L;
	
	public static final Int ONE = Int.valueOf(1);
	public static final Int ZERO = Int.valueOf(0);
	public static final Int MINUS_ONE = Int.valueOf(-1);
	
	public static Int valueOf (String value) {
		try{
		return MathStructuresFactory.getFactory().numberFor(Int.class , value);
		}catch (NumberFormatException e){
			throw new NumberFormatException(value + " is not an integer");
		}
	}
	
	public static Int valueOf (Numeral<?, ?> other) {
		if (Int.class.isInstance(other)){
			return Int.class.cast(other);
		} 
		return valueOf(other.asNumber());
	}
	
	public static Int valueOf (Number other) {
		return MathStructuresFactory.getFactory().numberFor(Int.class, other);
	}
	
	public static Int valueOf (long other) {
		return MathStructuresFactory.getFactory().numberFor(Int.class, new Long(other));
	}

	public Int min(Int other) {
		return this.compareTo(other) > 0 ? other : this;
	}

	public Int max(Int other) {
		return this.compareTo(other) > 0 ? this : other;
	}
	
	@Override
	public Int nextBy(Int other){
		return this.plus(other);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int previousBy(Int other) {
		return this.minus(other); 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int minus(Int other) {
		return this.plus(other.negate());
	}

	@Override
	public final Int abs(){
		// safe arithmetic
		if (this.isMinValue())
		{
			return this.promoteNextInt().abs();
		}
		return this.signum() < 0 ? this.negate() : this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Int previous() {
		return this.minus(Int.valueOf(1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Int next() {
		return this.plus(Int.valueOf(1));
	}
	
	@Override
    public final Int negate(){
		return IntCalculations.safeNegate(this);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Int plus(Number n) {
		 return this.plus(Int.valueOf(n));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(Int other) {
		return this.asNumber().compareTo(other.asNumber());
	}
	
	public final int compareTo(Number other) {
		return compareTo(Int.valueOf(other));
	}

	@Override
	public final Int toInt() {
		return this;
	}

	@Override
	public final Complex toComplex() {
		return Complex.rectangular(toReal(), RealAlgebricStructure.getInstance().zero());
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Int minus(Number n) {
		 return this.minus(Int.valueOf(n));
	}

	public final Int times(Number n) {
		return this.times(Int.valueOf(n));
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int plus (Int other){
		return IntCalculations.safeAdd(IntCalculations.promoteToSameRange(this, other));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int times(Int other) {
		return IntCalculations.safeMultiply(IntCalculations.promoteToSameRange(this, other));
	}
	
	/**
	 * Returns the integer division between this and other. This operation is equivalent to floor( this / other ).
	 * 
	 * @param other the operation dividend
	 * @return the quocient.
	 */
	public Int divide(Int other) {
		return IntCalculations.safeDivide(IntCalculations.promoteToSameRange(this, other));
	}
	
	public Rational over(Int other) {
		return MathStructuresFactory.getFactory().numberFor(Rational.class, this, other);
	}
	
	public final Rational over(Number n) {
		return this.over(Int.valueOf(n));
	}

	
	/**
	 * @return
	 */
	protected abstract Int diffToMax();

	/**
	 * @return
	 */
	protected abstract Int diffToMin();
	
	/**
	 * @param other
	 * @return
	 */
	protected abstract Int doPlus(Int other);

	
	/**
	 * @return
	 */
	protected abstract Int invertSign();

	/**
	 * @return
	 */
	protected abstract Int promoteNextInt();

	/**
	 * @return
	 */
	protected abstract boolean isMinValue();
	protected abstract boolean isMaxValue();

	protected final int rank(){
		return 0;
	}
	
	public final boolean isOdd(){
		return !isEven();
	}
	
	/**
	 * the exponent in the value range . 
	 * 8 bits = 2^3 -> 3
	 * 16 bits = 2^4 -> 4
	 * 32 bits = 2^5 -> 5
	 * 64 bits = 2^6 -> 6
	 * 128 bits = 2^7 -> 7
	 * @return
	 */
	protected abstract int rangeExponent();
	
	public abstract int asInteger();
	public abstract long asLong();
	public abstract BigInteger asBigInteger();
	
	public abstract boolean isEven();
	/**
	 * @return true if the value representend if between Interga.MinValue and Integer.MaxValue
	 */
	public abstract boolean isInteger();
	

	/**
	 * {@inheritDoc}
	 * 
	 * Allways return zero, a 1 / n is always a zero integer part.
	 */

	public final Rational inverse() {
		return MathStructuresFactory.getFactory().numberFor(Rational.class, this.getAlgebricStructure().one(), this);
	}
	
	/**
	 * @return
	 */
	protected abstract boolean isMinusOne();

	
	public Progression<Int, Int> upTo(Int other){
		return  new LinearProgression<Int>(this, other);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IntAlgebricStructure getAlgebricStructure() {
		return IntAlgebricStructure.getInstance();
	}

	/**
	 * @param byteCount
	 * @return
	 */
	public final Int promoteToInt(int rangeExponent){
		if (rangeExponent < this.rangeExponent()){
			throw new IllegalArgumentException("Cannot demote an Int while tring to promote it");
		} 
		
		if (rangeExponent == this.rangeExponent()){
			return this;
		}
		
		return this.promoteNextInt().promoteToInt(rangeExponent);
		
	}
	
	/**
	 * @return
	 */
	protected abstract Int maxOver();

	/**
	 * @return
	 */
	protected abstract Int minOver();

	/**
	 * Multiples this by other with garanteen that the values are safe to multiply in this bit range, i.e. it does not need to garrantee safe calculation 
	 * @param other 
	 * @return
	 */
	protected abstract Int doTimes(Int other);

	/**
	 * Divides this by other with garanteen that the values are safe to divide in this bit range, i.e. it does not need to garrantee safe calculation 
	 * @param other 
	 * @return
	 */
	protected abstract Int doDivide(Int other);

	/**
	 * @param d1
	 * @return
	 */
	public final Int[] divideAndRemainder(Int other) {
		BigInteger[] result = this.asBigInteger().divideAndRemainder(other.asBigInteger());
		return new Int[]{new BigInt(result[0]), new BigInt(result[1])};
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public static Int gcd(Int a, Int b) {
		if (a.isZero() && b.isZero()){
			return Int.ZERO;
		} else if (b.isZero()){
			return a.abs();
		} else if (a.isZero()){
			return gcd(b,a);
		} else if (a.signum() < 0 && b.signum() <0){
			return gcd(a.negate(),b.negate()).negate();
		} else if (a.signum() * b.signum() < 0 ){
			return gcd(a.abs(),b.abs());
		}
		
		Int absA = a.abs();
		Int absB = b.abs();
		
		Int remainder=null;  
		
	    do {
	      remainder = absA.divideAndRemainder(absB)[1];
	      absA = absB;
	      absB = remainder;
	    } while (remainder.signum() > 0);
	  
	    return absA.times(a.signum() * b.signum());
	}

}
