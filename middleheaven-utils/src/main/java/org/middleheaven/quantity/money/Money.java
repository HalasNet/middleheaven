/**
 * 
 */
package org.middleheaven.quantity.money;

import java.io.Serializable;

import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.structure.DecimalReal;
import org.middleheaven.quantity.measure.Amount;
import org.middleheaven.quantity.measure.AmountAlgebricStructure;
import org.middleheaven.quantity.measure.DecimalMeasure;
import org.middleheaven.quantity.measure.Measure;
import org.middleheaven.quantity.unit.Unit;

/**
 * 
 */
public class Money implements Serializable, Comparable<Money> , Amount<Money, org.middleheaven.quantity.measurables.Currency>{

	private static final long serialVersionUID = -6885304390183764842L;

	/**
	 * @param currency
	 * @return
	 */
	public static Money zero(Currency currency) {
		return new Money(Real.valueOf(0), currency);
	}

	
	/**
	 * @param i
	 * @param string
	 * @return
	 */
	public static Money valueOf(long amount, String currencyIsoCode) {
		return new Money(DecimalReal.valueOf(amount), Currency.currency(currencyIsoCode));
	}
	
	/**
	 * @param times
	 * @param targetCurency
	 * @return
	 */
	public static Money valueOf(Real times, Currency targetCurency) {
		return new Money(times, targetCurency);
	}
	
	/**
	 * @param string
	 * @param currencyIsoCode
	 * @return
	 */
	public static Money valueOf(String amount, String currencyIsoCode) {
		return new Money(DecimalReal.valueOf(amount), Currency.currency(currencyIsoCode));
	}
	
	/**
	 * @param string
	 * @param currencyIsoCode
	 * @return
	 */
	public static Money valueOf(String amount, Currency targetCurency) {
		return new Money(DecimalReal.valueOf(amount), targetCurency);
	}
	
	protected Currency currency;
	protected Real amount;
	
	protected Money(Real amount, Currency currency){
		this.amount = amount;
		this.currency = currency;
	}
	
	public Unit<org.middleheaven.quantity.measurables.Currency> unit(){
		return currency;
	}

	public Currency currency(){
		return currency;
	}

	public Money plus(Money other) {
		MoneyUtils.assertCompatible(this.currency, other.currency);
		return new Money(this.amount.plus(other.amount), this.currency);
	}
	
	public Money minus(Money other) {
		MoneyUtils.assertCompatible(this.currency, other.currency);
		return new Money(this.amount.minus(other.amount), this.currency);
	}
	
	public  Real amount(){
		return amount;
	}

	public Money times(Number factor){
		return times(Real.valueOf(factor));
	}
	
	public Money times(Real factor) {
		return new Money(this.amount.times(factor), this.currency);
	}
	
	public Money over(Real factor) {
		return new Money(this.amount.over(factor), this.currency);
	}


	
	public Real over(Money other){
		MoneyUtils.assertCompatible(this.currency, other.currency);
		return this.amount.over(other.amount);
	}
	
	@Override
	public final int compareTo(Money other) {
		if (!this.currency.equals(other.currency)){
			throw new IllegalArgumentException("Cannot compare money in different currencies.");
		}
		return  this.amount().compareTo(other.amount());
	}

	public boolean greaterThan(Money other) {
		return (compareTo(other) > 0);
	}

	public boolean lesserThan(Money other) {
		return (compareTo(other) < 0);
	}

	public boolean greaterThanOrEqual(Money other) {
		return (compareTo(other) >= 0);
	}

	public boolean lesserThanOrEqual(Money other) {
		return (compareTo(other) <= 0);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmountAlgebricStructure<Money, org.middleheaven.quantity.measurables.Currency> getAlgebricStructure() {
		return new AmountAlgebricStructure<Money,org.middleheaven.quantity.measurables.Currency> (new Money(Real.valueOf(0), currency));
	}

	
	public final boolean equals(Object other){
		return ((other instanceof Money)? equalsMoney((Money) other) : (other instanceof Measure && equalsMeasure((Measure) other)))  ;
	}
	
	/**
	 * @param other
	 * @return
	 */
	private boolean equalsMeasure(Measure other) {
		return  this.amount.equals(((DecimalMeasure)other).amount()) &&  this.currency.isCompatible(other.unit());
	}

	public int hashCode(){
		return amount.hashCode();
	}

	/**
	 * @param other
	 * @return
	 */
	protected boolean equalsMoney(Money other){
		return this.currency.equals(other.currency) && this.amount().equals(other.amount());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Money negate() {
		return new Money(amount.negate(), this.currency);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return this.amount.isZero();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isExact() {
		return true;
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public <T extends Measurable, U extends Measurable> Measure<T> times(Measure<U> other) {
//		return DecimalMeasure.exact(this.amount, this.unit()).times(other);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public <T extends Measurable, U extends Measurable> Measure<T> over(Measure<U> other) {
//		   return DecimalMeasure.exact(this.amount, this.unit()).over(other);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public <T extends Measurable> Measure<T> inverse() {
//		return DecimalMeasure.exact(this.amount, this.unit()).inverse();
//	}
//	/**
//	 * {@inheritDoc}
//	 */
//	public <O extends Measurable> Measure<O> cast(Class<O> measurableType) {
//		if (Currency.class.isAssignableFrom(measurableType)){
//			return (Measure<O>)this;
//		} else {
//			throw new IllegalArgumentException("Cannot cast Money to a measure of " + measurableType);
//		}
//	}

	public String toString(){
		return this.amount.toString() + " " + this.unit().toString();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Measure<org.middleheaven.quantity.measurables.Currency> asMeasure() {
		return DecimalMeasure.exact(this.amount, this.unit());
	}


}
