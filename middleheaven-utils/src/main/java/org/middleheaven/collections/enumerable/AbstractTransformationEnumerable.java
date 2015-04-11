/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public abstract class AbstractTransformationEnumerable<R,T> extends AbstractBaseEnumerable<R> {

	private AbstractBaseEnumerable<T> original;
	
	protected AbstractBaseEnumerable<T> original(){
		return original;
	}
	
	protected AbstractTransformationEnumerable(AbstractBaseEnumerable<T> original){
		this.original = original;
	}
}
