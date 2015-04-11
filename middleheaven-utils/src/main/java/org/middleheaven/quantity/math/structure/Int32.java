package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.middleheaven.quantity.math.Int;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.util.Hash;

class Int32 extends Int {

	private static final long serialVersionUID = 7891405602158515389L;

    private int value = 0;
	
	public Int32(String value) {
		this.value = Integer.parseInt(value);
	}
	
    Int32(int value) {
    	this.value = value;
	}

	/**
	 * @return
	 */
	public Int reduce() {
	   return this;
	}
	
	@Override
	public boolean isZero() {
		return value == 0;
	}

	@Override
	public BigDecimal asNumber() {
		return new BigDecimal(value);
	}

	@Override
	public Int invertSign() {
		return new Int32(-value);
	}

	@Override
	protected Int doPlus(Int other) {
		return new Int32(this.value + ((Int32)other).value);
	}

	@Override
	protected Int doTimes(Int other) {
		return new Int32(this.value * ((Int32)other).value);
	}

	@Override
	protected Int doDivide(Int other) {
		return new Int32(this.value/other.asNumber().intValue());
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public boolean isEven() {
		return this.value % 2 == 0 ;
	}

	protected boolean equalsOther(Int32 other){
		return  this.value == other.value;
	}

	@Override
	protected boolean equalsSame(Int other) {
		return equalsOther( (Int32) other);
	}

	@Override
	public Real toReal() {
		return Real.valueOf(Integer.valueOf(this.value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Hash.hash(this.value).hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInteger() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int asInteger() {
		return (int)value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long asLong() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int diffToMax() {
		return new Int32(Integer.MAX_VALUE - this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int diffToMin() {
		return new Int32(Integer.MIN_VALUE - this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int promoteNextInt() {
		return new Int64(this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMinValue() {
		return this.value == Integer.MIN_VALUE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMaxValue() {
		return this.value == Integer.MAX_VALUE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int maxOver() {
		return new Int32(Integer.MAX_VALUE/ this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int minOver() {
		return new Int32(Integer.MIN_VALUE/ this.value);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int rangeExponent() {
		return 5;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMinusOne() {
		return this.value == -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		return this.value == 1;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger asBigInteger() {
		return BigInteger.valueOf(this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int signum() {
		return this.value == 0 ? 0 : (this.value > 0 ? 1 : -1);
	}


	
}
