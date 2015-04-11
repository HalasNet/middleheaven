package org.middleheaven.quantity.convertion;

import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.RealAlgebricStructure;
import org.middleheaven.quantity.measure.DecimalMeasure;
import org.middleheaven.quantity.unit.Measurable;
import org.middleheaven.quantity.unit.Unit;

/**
 * 
 * @param <E>
 */
public final class AditiveConverter<E extends Measurable > extends AbstractUnitConverter<E> {

	/**
	 * 
	 * @param originalUnit
	 * @param resultUnit
	 * @param shift
	 * @return
	 */
	public static <E extends Measurable> AditiveConverter<E> convert(Unit<E> originalUnit,
			Unit<E> resultUnit, Real shift) {
		return new AditiveConverter<E>(shift,originalUnit, resultUnit );
	}
	
	private final Real shift;
	private AditiveConverter(Real shift , Unit<E> originalUnit, Unit<E> resultUnit) {
		super(originalUnit, resultUnit);
		this.shift = shift;
	}

	@Override
	public DecimalMeasure<E>  convertFoward(DecimalMeasure<E>  value) {
		if (!value.unit().equals(this.originalUnit)){
			throw new IllegalArgumentException("Expected unit " + this.originalUnit + " but was " + value.unit());
		}
		
		return UnitConversion.timesRefactor(value, RealAlgebricStructure.getInstance().one() , this.resultUnit).minus(DecimalMeasure.measure(shift,  RealAlgebricStructure.getInstance().zero(), this.resultUnit));
	}

	@Override
	public DecimalMeasure<E>  convertReverse(DecimalMeasure<E>  value) {
		if (!value.unit().equals(this.resultUnit)){
			throw new IllegalArgumentException("Expected unit " + this.originalUnit + " but was " + value.unit());
		}
		return UnitConversion.timesRefactor(value, RealAlgebricStructure.getInstance().one() , this.resultUnit).plus(DecimalMeasure.measure(shift,  RealAlgebricStructure.getInstance().zero(), this.resultUnit));
	}



}
