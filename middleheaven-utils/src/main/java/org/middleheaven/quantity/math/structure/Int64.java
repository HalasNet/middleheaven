package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.middleheaven.quantity.math.Int;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.util.Hash;

class Int64 extends Int {

	private static final long serialVersionUID = 7891405602158515389L;

    private long value = 0;
	
	public Int64(String value) {
		this.value = Long.parseLong(value);
	}
	
    Int64(long value) {
    	this.value = value;
	}

	@Override
	public boolean isZero() {
		return value ==0;
	}

	@Override
	public BigDecimal asNumber() {
		return new BigDecimal(value);
	}

	@Override
	public Int invertSign() {
		return new Int64(-value);
	}

	@Override
	protected Int doPlus(Int other) {
		return new Int64(this.value + ((Int64)other).value);
	}

	@Override
	protected Int doTimes(Int other) {
		return new Int64(this.value * ((Int64)other).value);
	}

	@Override
	protected Int doDivide(Int other) {
		return new Int64(this.value/other.asNumber().longValue());
	}
	
	@Override
	public String toString() {
		return Long.toString(value);
	}

	@Override
	public boolean isEven() {
		return this.value % 2 == 0 ;
	}

	protected boolean equalsOther(Int64 other){
		return  this.value == other.value;
	}

	@Override
	protected boolean equalsSame(Int other) {
		return equalsOther( (Int64) other);
	}

	@Override
	public Real toReal() {
		return Real.valueOf(Long.valueOf(this.value));
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
		long min = Integer.MIN_VALUE;
		long max = Integer.MAX_VALUE;
		return this.value >= min && this.value <= max;
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
		return new Int64(Long.MAX_VALUE - this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int diffToMin() {
		return new Int64(Long.MIN_VALUE - this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int promoteNextInt() {
		return new BigInt(this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMinValue() {
		return this.value == Long.MIN_VALUE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isMaxValue() {
		return this.value == Long.MAX_VALUE;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int maxOver() {
		return new Int64(Long.MAX_VALUE/ this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int minOver() {
		return new Int64(Long.MIN_VALUE/ this.value);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int rangeExponent() {
		return 6;
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
	public int signum() {
		return this.value == 0 ? 0 : (this.value < 0 ? -1 : 1);
	}

	/**
	 * @return
	 */
	public Int reduce() {
		long min = Integer.MIN_VALUE;
		long max = Integer.MAX_VALUE;
		 if (this.value > min && this.value < max){
			 return new Int32((int)value).reduce();
		 } else {
			 return this;
		 }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger asBigInteger() {
		return BigInteger.valueOf(this.value);
	}


}
