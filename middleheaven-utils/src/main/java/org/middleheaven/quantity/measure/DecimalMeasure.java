package org.middleheaven.quantity.measure;

import org.middleheaven.quantity.convertion.Convertable;
import org.middleheaven.quantity.convertion.UnitConversion;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.RealAlgebricStructure;
import org.middleheaven.quantity.unit.IncompatibleUnitsException;
import org.middleheaven.quantity.unit.Measurable;
import org.middleheaven.quantity.unit.SI;
import org.middleheaven.quantity.unit.Unit;

/**
 * A real measure. <code>DecimalMeasure</code> is the generic and default form for explictly
 * handle measures. You may find useful to use <code>AngularMeasure</code> for angular measures
 *
 * @param <E>
 * @see {@link org.middleheaven.quantity.measure.AngularMeasure}
 */
public class DecimalMeasure<E extends Measurable> extends AbstractUncertainMeasure<E> 
	implements Comparable<DecimalMeasure<E>>, 
		Convertable<E,DecimalMeasure<E>> {


	/**
	 * 
	 * @param unit
	 * @return
	 */
	public static <T extends Measurable> DecimalMeasure<T> zero(Unit<T> unit) {
		return exact(RealAlgebricStructure.getInstance().zero(), unit);
	}
	
	/**
	 * 
	 * @param unit
	 * @return
	 */
	public static <T extends Measurable> DecimalMeasure<T> one(Unit<T> unit) {
		return exact(RealAlgebricStructure.getInstance().zero(), unit);
	}
	
	/**
	 * 
	 * @param value
	 * @param uncertainty
	 * @param unit
	 * @return
	 */
	public static <T extends Measurable> DecimalMeasure<T> measure(java.lang.Number value,java.lang.Number uncertainty, Unit<T> unit){
		return measure( Real.valueOf(value),Real.valueOf(uncertainty), unit);
	}
	
	/**
	 * 
	 * @param value
	 * @param unit
	 * @return
	 */
	public static <T extends Measurable> DecimalMeasure<T> exact(java.lang.Number value, Unit<T> unit){
		return measure( Real.valueOf(value),RealAlgebricStructure.getInstance().zero(), unit);
	}
	
	/**
	 * 
	 * @param value
	 * @param uncertainty
	 * @param unit
	 * @return
	 */
	public static <T extends Measurable> DecimalMeasure<T> measure(Real value, Real uncertainty,Unit<T> unit){
		return new DecimalMeasure<T>(value,uncertainty,unit);
	}

	public static <T extends Measurable> DecimalMeasure<T> exact(Real value, Unit<T> unit){
		return new DecimalMeasure<T>(value,value.minus(value),unit);
	}
	
	protected DecimalMeasure(Real amount, Real uncertainty, Unit<E> unit) {
		super(amount, uncertainty, unit);
	}


	public DecimalMeasure<E> negate() {
		return new DecimalMeasure<E>(this.amount.negate(),this.absUncertainty, this.unit);
	}
	
	public DecimalMeasure<E> over(Real factor) {
		return new DecimalMeasure<E>(this.amount.over(factor), this.absUncertainty.over(factor), this.unit);
	}
	
	@Override
	public  DecimalMeasure<E> times(Real factor) {
		return new DecimalMeasure<E>(this.amount.times(factor), this.absUncertainty.times(factor), this.unit);
	}

	public <T extends Measurable> DecimalMeasure<T>  inverse() {
		Unit<T> unit = SI.DIMENTIONLESS.over(this.unit);
		return new DecimalMeasure<T>(this.amount.inverse(), this.absUncertainty, unit);
	}
	
	public DecimalMeasure<E>  one() {
		return exact(RealAlgebricStructure.getInstance().one(),this.unit);
	}

	public DecimalMeasure<E>  zero() {
		return exact(RealAlgebricStructure.getInstance().zero(),this.unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Measurable, U extends Measurable> DecimalMeasure<T> times(Measure<U> other) {
		Unit<T> u = this.unit.times(other.unit());
		DecimalMeasure<U> convertedOther = UnitConversion.convert(other, other.unit());
		return new DecimalMeasure<T>(this.amount().times(convertedOther.amount()),timesError(convertedOther),u);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Measurable, U extends Measurable> DecimalMeasure<T> over(Measure<U> other) {
		Unit<T> u = this.unit.over(other.unit());
		DecimalMeasure<U> convertedOther = UnitConversion.convert(other, other.unit());
		return new DecimalMeasure<T>(this.amount().over(convertedOther.amount()),overError(convertedOther),u);
	}
	
	public DecimalMeasure<E> plus(Measure<E> other) throws IncompatibleUnitsException {
		// (a+e) + (b+u) = (a+b) + (e+u) 
		assertCompatible (other);
		DecimalMeasure<E> convertedOther = UnitConversion.convert(other, this.unit);
		return new DecimalMeasure<E>(convertedOther.amount().plus(this.amount), this.absUncertainty.plus(convertedOther.uncertainty()) , this.unit.plus(convertedOther.unit()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DecimalMeasure<E> minus(Measure<E> other) {
		// (a+e) - (b+u) = (a-b) + (e+u) 
		assertCompatible (other);
		DecimalMeasure<E> convertedOther = UnitConversion.convert(other, this.unit);
		return new DecimalMeasure<E>(this.amount().minus(convertedOther.amount), this.absUncertainty.plus(convertedOther.uncertainty()) , this.unit.minus(convertedOther.unit()));
	}


	@Override
	public  int compareTo(DecimalMeasure<E> other) {
		assertCompatible (other);
		return this.amount.compareTo(other.amount);
	}

	@SuppressWarnings("unchecked")
	public <T extends Measurable > Measure<T> cast() {
		return (DecimalMeasure<T>) this;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public <O extends Measurable> Measure<O> cast(Class<O> measurableType) {
		return (Measure<O>)this;
	}
	


	/**
	 * 
	 * @param unit
	 * @return
	 */
	public <T extends Measurable> DecimalMeasure<T> sqrt(Unit<T> unit) {
		Real r = Real.valueOf(Math.sqrt(this.amount.asNumber().doubleValue()));
		Real u = Real.valueOf(Math.sqrt(this.absUncertainty.asNumber().doubleValue()));
		return new DecimalMeasure<T>(r, u, unit);  
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public MeasureStructure<E> getAlgebricStructure() {
		return new MeasureStructure<>(zero(this.unit));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		 return this.amount.isZero() && this.absUncertainty.isZero();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DecimalMeasure<E> convertTo(Unit<E> newUnit) {
		return UnitConversion.convert(this, newUnit);
	}


	

}
