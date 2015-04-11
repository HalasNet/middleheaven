package org.middleheaven.quantity.math;

import org.middleheaven.quantity.math.structure.FieldElement;
import org.middleheaven.quantity.math.structure.MathStructuresFactory;

/**
 * Complex field element.
 */
public abstract class Complex extends Numeral<Complex, ComplexAlgebricStructure> implements FieldElement<Complex, ComplexAlgebricStructure> ,Conjugatable<Complex> {

	private static final long serialVersionUID = 5580549238295872790L;
	
	/**
	 * Creates a complex number form it's polar representation.
	 * @param magnitude the magnitude of the complex.
	 * @param phase the phase of the complex.
	 * @return the complex number.
	 */
	public static Complex polar(Real magnitude, Real phase) {
		
		Real real = magnitude.times(phase.cos());
		Real imaginary = magnitude.times(phase.sin());
		return (Complex)MathStructuresFactory.getFactory().numberFor(Complex.class , real ,  imaginary);
	}
	
	/**
	 * Creates a complex number form it's retangular representation.
	 * @param real the real parte of the number
	 * @param imaginary the imaginary part of the number
	 * @return the complex number.
	 */
	public static Complex rectangular(Real real, Real imaginary) {
		return (Complex)MathStructuresFactory.getFactory().numberFor(Complex.class , real ,  imaginary);
	}
	
	/**
	 * Creates a complex number form it's retangular representation.
	 * @param real the real parte of the number
	 * @param imaginary the imaginary part of the number
	 * @return the complex number.
	 */
	public static Complex rectangular(java.lang.Number real, java.lang.Number imaginary) {
		return (Complex)MathStructuresFactory.getFactory().numberFor(Complex.class , Real.valueOf(real), Real.valueOf(imaginary));
	}
	
	/**
	 * Creates a pure real complex.
	 * @param real the real part of the number.
	 * @return the complex number.
	 */
	public static Complex real(java.lang.Number real) {
		return rectangular(real, 0d);
	}
	
	/**
	 * Creates a pure imaginary complex.
	 * @param imaginary the imaginary part of the number.
	 * @return the complex number.
	 */
	public static Complex imaginary(java.lang.Number imaginary) {
		return rectangular(0d, imaginary);
	}
	
	/**
	 * Creates a complex number form it's string retangular representation.
	 * @param value a retangular representation of the complex in the form a+ib 
	 * @return the complex number.
	 */
	public static Complex rectangular(String value) {
		return (Complex)MathStructuresFactory.getFactory().numberFor(Complex.class ,value);
	}
	
	public static Complex ONE(){
		return rectangular(1, 0);
	}

	public static Complex ZERO(){
		return rectangular(0, 0);
	}
	
	public static Complex I(){
		return rectangular(0, 1);
	}
	
//	@Override
//	public Complex promote(Numeral other) {
//		return MathStructuresFactory.getFactory().promote(other, Complex.class); 
//	}
	
	/**
	 * @return this complex number conjugate. The conjugate of c is denotes c* and is calculated 
	 * from c (c= a + ib) as c* = a - ib
	 */
	public abstract Complex conjugate();
	
	/**
	 * 
	 * @return the real part of this complex
	 */
	public abstract Real toReal();
	
	/**
	 * 
	 * @return the imaginary part of this complex as a Real number
	 */
	public abstract Real toImaginary();
	
	/**
	 * Returns the magnitude of this complex. The magnitude of c= a +ib is 
	 * |c| = a<sup>2</sup> + b<sup>2</sup> 
	 * @return the magnitude of this complex. 
	 */
	public abstract Real magnitude();
	

	public abstract Complex times(Number n);

	public abstract Complex over(Number n);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComplexAlgebricStructure getAlgebricStructure() {
		return ComplexAlgebricStructure.getInstance();
	}



}
