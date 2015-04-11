/**
 * 
 */
package org.middleheaven.collections.enumerable;


/**
 * Operates in some form over the original enumerable
 */
abstract class AbstractOperationalEnumerable<T> extends AbstractBaseEnumerable<T> {

	private AbstractBaseEnumerable<T> original;
	
	
	
	protected AbstractBaseEnumerable<T> original(){
		return original;
	}
	
	protected AbstractOperationalEnumerable(AbstractBaseEnumerable<T> original){
		this.original = original;
	}



}
