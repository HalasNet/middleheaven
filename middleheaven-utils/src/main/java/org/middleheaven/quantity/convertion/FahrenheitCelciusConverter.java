package org.middleheaven.quantity.convertion;

import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.measurables.Temperature;
import org.middleheaven.quantity.measure.DecimalMeasure;
import org.middleheaven.quantity.unit.NonSI;

public class FahrenheitCelciusConverter extends AbstractUnitConverter<Temperature> {

	private static final Real MULTIPLICATIVE_FACTOR = Real.rational(5, 9); 
	protected FahrenheitCelciusConverter() {
		super(NonSI.FAHRENHEIT, NonSI.CELSIUS);
	}


	public DecimalMeasure<Temperature> convertFoward(DecimalMeasure<Temperature>  farhrenheit) {
		if (!farhrenheit.unit().dimension().equals(this.originalUnit.dimension())){
			throw new IllegalArgumentException("Dimention is not Temperature");
		}
		
		final DecimalMeasure<Temperature>  ZERO_CELCIUS_IN_FAHRENHEIT =  DecimalMeasure.measure(32, 0, NonSI.FAHRENHEIT);
		
		return UnitConversion.timesRefactor(farhrenheit.minus(ZERO_CELCIUS_IN_FAHRENHEIT), MULTIPLICATIVE_FACTOR , this.resultUnit);
	}

	public  DecimalMeasure<Temperature>  convertReverse(DecimalMeasure<Temperature>  celcius) {
		
		final DecimalMeasure<Temperature>  ZERO_FAHRENHEIT_IN_CELCIUS =  DecimalMeasure.measure(32, 0, NonSI.CELSIUS);
		
		return UnitConversion.timesRefactor(celcius, MULTIPLICATIVE_FACTOR.inverse() , this.resultUnit).plus(ZERO_FAHRENHEIT_IN_CELCIUS);
		
	}

}
