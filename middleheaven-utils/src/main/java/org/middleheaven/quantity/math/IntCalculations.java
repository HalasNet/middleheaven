/**
 * 
 */
package org.middleheaven.quantity.math;

import org.middleheaven.quantity.math.structure.BigInt;

/**
 * 
 */
class IntCalculations {

	private IntCalculations (){}
	
	public static Int[] promoteToSameRange(Int ... values ){
		int ia = values[0].rangeExponent();
		int ib = values[1].rangeExponent();
		
		if (ia < ib){
			values[0] = values[0].promoteToInt(values[1].rangeExponent());
		} else if (ia > ib){
			values[1] = values[1].promoteToInt(values[0].rangeExponent());
		}
		return values;
	}
	
	public static Int safeNegate(Int it ){
		// safe arithmetic
		if (it.isMinValue())
		{
			return safeNegate(it.promoteNextInt());
		}
		return it.invertSign();
	}
	
	public static Int safeAdd(Int ... values ){
		if (values[0] instanceof BigInt){
			return values[0].plus(values[1]);
		} else if (values[1] instanceof BigInt){
			return values[1].plus(values[0]);
		}
		
		Int right = values[0];
		Int left = values[1];
		if (right.signum() > 0 ? left.compareTo(right.diffToMax()) > 0 : left.compareTo(right.diffToMin()) < 0){
			return safeAdd(right.promoteNextInt() , left.promoteNextInt());
		}
		return right.doPlus(left);
	}

	/**
	 * @param promoteToSameRange
	 * @return
	 */
	public static Int safeMultiply(Int[] values) {
		
		if (values[0] instanceof BigInt){
			return values[0].times(values[1]);
		} else if (values[1] instanceof BigInt){
			return values[1].times(values[0]);
		}
		
		final boolean leftIsMinusOne = values[1].isMinusOne();
		if (values[0].isZero()){
			return values[0];
		} else if (values[0].isOne()){
			return values[1];
		} else if (values[0].isMinusOne()){
			if (leftIsMinusOne){
				// equivalent to -1 x -1 
				return values[0].getAlgebricStructure().one();
			}
			return safeNegate(values[1]);
		} else {
			if (values[1].isZero()){
				return values[1];
			} else if (values[1].isOne()){
				return values[0];
			} else if (leftIsMinusOne){
				return safeNegate(values[0]);
			} else {
				return safeMultiplyNoSpecialValues(values);
			}
		}
	}
	
	private static Int safeMultiplyNoSpecialValues(Int ... values) { 	// no value is -1, 1 or 0
		Int right = values[0];
		Int left = values[1];
	
		if (right.rangeExponent() < 7 && right.rangeExponent() < 7){
			final Int maxOverRight = right.maxOver();
			final Int minOverRight = right.minOver();
			if ((right.signum() > 0 && (left.compareTo(maxOverRight) > 0 || left.compareTo(minOverRight) < 0)) 
					|| (right.signum() < 0  && left.compareTo(minOverRight) > 0 || left.compareTo(maxOverRight) < 0) ) {		
				return safeMultiplyNoSpecialValues(values[0].promoteNextInt(), values[1].promoteNextInt());
		    }
		}

		return right.doTimes(left);
			
	}

	/**
	 * @param promoteToSameRange
	 * @return
	 */
	public static Int safeDivide(Int[] values) {
		
		if (values[1].isZero()){
			throw new ArithmeticException("Division by zero");
		} else if (values[1].isOne()){
			return values[0];
		} else if (values[1].isMinusOne()){
			return safeNegate(values[0]);
		} else if (values[0].isZero() || values[0].isOne() || values[0].isMinusOne()){
			return values[0];
		} else {
			return values[0].doDivide(values[1]);
		}	
	}
	
}
