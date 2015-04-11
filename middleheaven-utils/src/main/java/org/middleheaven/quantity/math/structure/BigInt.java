/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.middleheaven.quantity.math.Int;
import org.middleheaven.quantity.math.Real;

/**
 * 
 */
public class BigInt extends Int{


	private static final long serialVersionUID = -8701558249323744074L;
	
	private BigInteger value;

	public BigInt(BigInteger value){
		this.value = value;
	}
	
	BigInt(long value){
		this.value = BigInteger.valueOf(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int invertSign() {
		return new BigInt(this.value.negate());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int asInteger() {
		return value.intValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long asLong() {
		return value.longValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEven() {
		return value.getLowestSetBit() != 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isInteger() {
		return value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0 && value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <=0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Real toReal() {
		return Real.valueOf(this.value.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal asNumber() {
		 return new BigDecimal(this.value.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return value.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return value.signum() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int diffToMax() {
		throw new UnsupportedOperationException("There is no max value");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int diffToMin() {
		throw new UnsupportedOperationException("There is no min value");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int plus(Int other) {
		return doPlus((BigInt)other.promoteToInt(this.rangeExponent()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int doPlus(Int other) {
		return new BigInt(this.value.add(((BigInt)other).value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int times(Int other) {
		return doTimes((BigInt)other.promoteToInt(this.rangeExponent()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int doTimes(Int other) {
		return new BigInt(this.value.multiply(((BigInt)other).value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int doDivide(Int other) {
		return new BigInt(this.value.divide(((BigInt)other).value));
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int promoteNextInt() {
		throw new ArithmeticException("Can not promote above BigInt");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMinValue() {
		return false; // there is not limit
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMaxValue() {
		return false; // there is not limit
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int rangeExponent() {
		return 7;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMinusOne() {
		return this.value.compareTo(BigInteger.ONE.negate()) == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		return this.value.compareTo(BigInteger.ONE) == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int maxOver() {
		throw new UnsupportedOperationException("There is no max value"); // TODO verify
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int minOver() {
		throw new UnsupportedOperationException("There is no min value"); // TODO verify
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int signum() {
		return this.value.signum();
	}

	/**
	 * @return
	 */
	public Int reduce() {
		if (this.value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >0 && this.value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 0){
			return new Int32(this.value.intValue()).reduce();
		} else if (this.value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) > 0 && this.value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) < 0){
			return new Int64(this.value.longValue());
		} else {
			return this;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equalsSame(Int other) {
		return this.value.compareTo(((BigInt)other).value) ==0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger asBigInteger() {
		return value;
	}

}
