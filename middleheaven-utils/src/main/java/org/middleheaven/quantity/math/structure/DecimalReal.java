/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;

import org.middleheaven.quantity.math.Int;
import org.middleheaven.quantity.math.Numeral;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.RealAlgebricStructure;
import org.middleheaven.util.Hash;

/**
 * Represents a real number in the form a + bx10^(-s) where a and b are longs and s is integer
 * 
 * Examples : 20.05 = 20 + 5 x10^(-2)
 * 			  0  = 0 + 0 x 10^(-0)
 *            1  = 1 + 0 x 10^(-0)
 *            0.00034534 = 0 + 34534x10^(-4)
 *            -45.78 =  -45 + (-78)x10^(-2)
 *  
 */
public class DecimalReal extends Real {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3783482974974946750L;

	public static DecimalReal valueOf(String value){
		int pos = value.indexOf('.');
		if (pos < 0){
			// integer
			return new DecimalReal(Long.parseLong(value),0,0);
		} else {
			return new DecimalReal(Long.parseLong(value.substring(0,pos)),Long.parseLong(value.substring(pos+1)),value.length() - pos-1);
		}
	}
	
	public static DecimalReal valueOf(long value){
		return new DecimalReal(value , 0 , 0);
	}

	private long integer;
	private long factor;
	private int scale; // positive is the work hipotesis
	
    private DecimalReal(long integer, long factor, int scale){
		this.integer = integer;
		this.factor = factor;
		this.scale = scale;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Real other) {
		return this.asNumber().compareTo(other.asNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real abs() {
		return new DecimalReal(Math.abs(integer), Math.abs(factor), scale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real negate() {
		return new DecimalReal(-integer, -factor, scale);
	}
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real plus(Real real) {
		if (real instanceof DecimalReal){
			DecimalReal other = (DecimalReal)real;
			int newScale = Math.max(this.scale, other.scale);
			
			return new DecimalReal(
					this.integer + other.integer, 
					reScale(this.factor, this.scale, newScale ) + reScale(other.factor, other.scale, newScale ), 
					newScale
			);
			
		}  else {
			Int pow = Int.valueOf(Math.pow(10, scale));
			return Fraction.reduce(Int.valueOf(integer).times(pow).plus(Int.valueOf(factor)), pow).plus(real);
		}
	}
	
	/**
	 * @param factor2
	 * @param scale2
	 * @param newScale
	 * @return
	 */
	private long reScale(long f, int s, int maxScale) {
		if (s == maxScale){
			return f;
		} else {
			return f * (long)Math.pow(10, maxScale -s);
		}
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real times(Real real) {
		if (real instanceof DecimalReal){
			// a+bE(-s) x x+yE(-u) = ax + byE(-s-u) + ayE(-u) + xbE(-s) 
			DecimalReal other = (DecimalReal)real;
			int maxScale = this.scale + other.scale;
			long b = reScale(this.factor, this.scale, maxScale);
			long y = reScale(other.factor, other.scale, maxScale);
			
			return new DecimalReal(
					this.integer * other.integer, 
					this.integer * y + other.integer * b + (y*b) / ((long)Math.pow(10, maxScale)), 
					maxScale
			);
			
		} else {
			Int pow = Int.valueOf(Math.pow(10, scale));
			return Fraction.reduce(Int.valueOf(integer).times(pow).plus(Int.valueOf(factor)), pow).times(real);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real inverse() {
		//  1 / (a +bE-s) = Es / (aEs + b)
		Int pow = Int.valueOf(Math.pow(10, scale));
		return Fraction.reduce(pow, Int.valueOf(integer).times(pow).plus(Int.valueOf(factor)));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real over(Real real) {
		return this.times(real.inverse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equalsSame(Real other) {
		return this.asNumber().compareTo(other.asNumber()) == 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Numeral<? super Real, RealAlgebricStructure> other) {
		return this.asNumber().compareTo(other.asNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real nextBy(Real increment) {
		return this.plus(increment);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Real previousBy(Real increment) {
		return this.minus(increment);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWhole() {
		return factor == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int toInt() {
		if (scale < 0){
			return Int.valueOf(integer + factor * Math.pow(10, scale) );
		} else {
			return Int.valueOf(integer);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int signum() {
		return Long.signum(integer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal asNumber() {
		return BigDecimal.valueOf(integer).add(BigDecimal.valueOf(factor).movePointLeft(scale)); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Hash.hash(integer).hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return integer == 0 && factor == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		 return  integer + "+" + factor + "E(" + (-scale) + ") = " + asNumber().toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOne() {
		throw new UnsupportedOperationException("Not implememented yet");
	}




}
