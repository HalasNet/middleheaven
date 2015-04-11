/**
 * 
 */
package org.middleheaven.util;

import org.middleheaven.util.coersion.TypeCoercing;

/**
 * 
 */
public class MaybeCharSequence<C extends CharSequence> extends Maybe<C>{
		
	/**
	 * Constructor.
	 * @param value
	 */
	protected MaybeCharSequence(C value) {
		super(value);
	}
	
	public <R> Maybe<R> coerce(Class<R> otherType){
		return this.isAbsent() ? Maybe.<R>absent() : of(TypeCoercing.coerce(this.value, otherType));
	}

}
