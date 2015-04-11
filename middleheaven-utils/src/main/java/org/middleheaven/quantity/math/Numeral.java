package org.middleheaven.quantity.math;


import java.io.Serializable;
import java.math.BigDecimal;

import org.middleheaven.quantity.Quantity;
import org.middleheaven.quantity.math.structure.AlgebricStructure;
import org.middleheaven.quantity.math.structure.AlgebricStructureElement;
import org.middleheaven.quantity.measurables.Dimensionless;
import org.middleheaven.quantity.unit.SI;
import org.middleheaven.quantity.unit.Unit;

/**
 * Represents a dimensionless <code>Quantity</code>, a number.
 * 
 */
public abstract class Numeral<T extends Numeral<T, A>, A extends AlgebricStructure< T,A>> implements AlgebricStructureElement<T , A> , 
	Quantity<Dimensionless> , Serializable  {


	private static final long serialVersionUID = -3007304512447112381L;

	public final Unit<Dimensionless> unit() {
		return SI.DIMENTIONLESS;
	}

	public abstract T abs();

	
	public abstract int signum();
	public abstract Int toInt();
	public abstract Real toReal();
	public abstract Complex toComplex();
	
	public abstract T plus (java.lang.Number n);
	
	public abstract T minus (java.lang.Number n);

	
	protected abstract int rank();
	



	public abstract BigDecimal asNumber();
	
	public abstract int hashCode();
	
	@SuppressWarnings("rawtypes")
	public final boolean equals(Object other){
		return other instanceof Numeral && equalsOther((Numeral)other);
	}
	
	protected boolean equalsOther(@SuppressWarnings("rawtypes") Numeral other){
		if (this.getClass().isInstance(other)){
			@SuppressWarnings("unchecked") T n = (T) this.getClass().cast(other);
			return this.equalsSame(n);
		} else {
			return this.asNumber().compareTo(other.asNumber())==0;
		}
	}
	
	protected abstract boolean equalsSame(T other);

	public abstract boolean isZero();
	
	public abstract String toString();

   

}
