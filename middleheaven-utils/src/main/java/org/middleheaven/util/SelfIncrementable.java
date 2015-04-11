/**
 * 
 */
package org.middleheaven.util;

import org.middleheaven.quantity.time.DiscreteIncrementable;

/**
 * 
 */
public interface SelfIncrementable<S extends SelfIncrementable<S>> extends StepIncrementable<S , S> , DiscreteIncrementable<S> {

}
