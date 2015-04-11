/**
 * 
 */
package org.middleheaven.util.function;



/**
 * A specific function that determined the logic value based on an object
 */
@FunctionalInterface
public interface Predicate<T> extends java.util.function.Predicate<T> {
	
	public default Predicate<T> negate() {
		return new NegatedPredicate<>(this);
	}

}
