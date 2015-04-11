/**
 * 
 */
package org.middleheaven.quantity.math;


/**
 * 
 */
public abstract class Rational extends Real{

	private static final long serialVersionUID = -4806853999855556700L;

	public abstract Rational negate();
	public abstract Rational inverse();
	public abstract Rational abs();

	public abstract Int floor ();
	public abstract Int ceil();

	public abstract Int numerator();
	public abstract Int denominator();

	public final Rational plus(Rational other){
		return ratioOf( 
				this.numerator().times(other.denominator()).plus((other.numerator().times(this.denominator()))) , 
				this.denominator().times(other.denominator())
				);
	}
	public final Rational minus(Rational other){
		return ratioOf( 
				this.numerator().times(other.denominator()).minus((other.numerator().times(this.denominator()))) , 
				this.denominator().times(other.denominator())
				);
	}

	public Rational times(Rational other){
		return ratioOf( 
				this.numerator().times(other.numerator()) , 
				this.denominator().times(other.denominator())
				);
	}
	
	public Rational over(Rational other){
		return ratioOf( 
				this.numerator().times(other.denominator()) , 
				this.denominator().times(other.numerator())
				);
	}
	

	protected abstract Rational ratioOf(Int numerator, Int denominator);
}
