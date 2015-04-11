/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 */
public class LongRatio {

	private static final int SCALE = 22;

	private long numerator;
	private long denominator;

	public static LongRatio valueOf (long num , long den){
		long gcd = gcd (num, den);

		if (gcd == 0 ){
			return new LongRatio(num, den);
		} else {
			return new LongRatio(num / gcd,  den / gcd);
		}
	}

	private LongRatio(long num , long den){
		this.numerator = num;
		this.denominator = den;
	}

	private static long gcd (long a, long b){

		long crossSign = Long.signum(a) * Long.signum(b);

		if (a ==0 && b  ==0){
			return 0;
		} else if (b ==0){
			return Math.abs(a);
		} else if (a ==0){
			return gcd(b,a);
		} else if (a < 0 && b <0){
			return -gcd(-a,-b);
		} else if ( crossSign < 0 ){
			return gcd(Math.abs(a),Math.abs(b));
		}

		long absA = Math.abs(a);
		long absB = Math.abs(b);

		long remainder =0;
		do {
			remainder = absA % absB;
			absA = absB;
			absB = remainder;
		} while (remainder > 0);

		return absA * crossSign;
	}

	public BigDecimal asNumber() {
		return numerator ==0 
				? BigDecimal.ZERO 
						: (denominator == 1L
						? new BigDecimal(numerator) 
						: new BigDecimal(numerator).divide(new BigDecimal(denominator), SCALE, RoundingMode.HALF_EVEN));
	}


	public LongRatio inverse(){
		if (numerator == 0){
			throw new ArithmeticException("Can not invert zero");
		}
		return new LongRatio(denominator, numerator);
	}
	
	public LongRatio negate() {
		if (denominator >0){
			// negate numerator
			return new LongRatio(-numerator, denominator);
		} else {
			// negate denominator
			return new LongRatio(numerator, -denominator);
		}
	}
	
	public LongRatio plus (LongRatio other){
		// a/b + c/d = (a*d+b*c)/bd !! may overflow!!
		return new LongRatio(this.numerator*other.denominator + this.denominator*other.numerator, this.denominator* other.denominator);
	}
}
