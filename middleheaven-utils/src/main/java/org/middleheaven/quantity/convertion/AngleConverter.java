package org.middleheaven.quantity.convertion;

import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.measurables.Angle;
import org.middleheaven.quantity.measure.DecimalMeasure;
import org.middleheaven.quantity.unit.SI;

public class AngleConverter extends AbstractUnitConverter<Angle> {

	
	private static final Real CONVERTION_FACTOR = Real.valueOf(180).over(Math.PI);
	
	protected AngleConverter() {
		super(SI.RADIANS, SI.DEGREE);
	}


	public DecimalMeasure<Angle> convertFoward(DecimalMeasure<Angle> radians) {
		return UnitConversion.timesRefactor(radians, CONVERTION_FACTOR , this.resultUnit);
	}


	public DecimalMeasure<Angle> convertReverse(DecimalMeasure<Angle> degree) {
		return UnitConversion.timesRefactor(degree, CONVERTION_FACTOR.inverse(), this.resultUnit);
	}





}
