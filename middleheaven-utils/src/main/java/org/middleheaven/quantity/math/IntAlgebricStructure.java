/**
 * 
 */
package org.middleheaven.quantity.math;

import java.util.Comparator;
import java.util.Random;

import org.middleheaven.collections.ComparableComparator;
import org.middleheaven.quantity.math.structure.MathStructuresFactory;
import org.middleheaven.quantity.math.structure.OrderedComutativeRing;
import org.middleheaven.quantity.math.structure.OrderedField;

/**
 * The Field of Reals
 */
public class IntAlgebricStructure implements OrderedComutativeRing<Int, IntAlgebricStructure> {

	
	private static final IntAlgebricStructure ME = new IntAlgebricStructure();
	
	public static IntAlgebricStructure getInstance(){
		return ME;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int zero() {
		return MathStructuresFactory.getFactory().numberFor( Int.class, "0");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int one() {
		return MathStructuresFactory.getFactory().numberFor( Int.class , "1");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGroupAdditive() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRing() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isField() {
		return true;
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Int fromNumber(Number n) {
//		return Int.valueOf(n);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Int random() {
//		return Int.valueOf(Math.random());
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Int random(Random random) {
//		return Int.valueOf(random.nextInt());
//	}

	@Override
	public Comparator<Int> getComparator() {
		return ComparableComparator.<Int>getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int minus(Int a, Int b) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int plus(Int a, Int b) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int multiply(Int a, Int b) {
		throw new UnsupportedOperationException("Not implememented yet");
	}


}
