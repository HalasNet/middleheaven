/**
 * 
 */
package org.middleheaven.collections.enumerable;

import org.middleheaven.util.Joiner;

/**
 * 
 */
public class StringSink implements Sink<String> {

	private Enumerable<String> enumerable;

	/**
	 * Constructor.
	 * @param it
	 */
    StringSink(Enumerable<String> it) {
		this.enumerable = it;
	}

	/**
	 * Converters all elements to a string separated by a given separator. 
	 * Elements are converted invoking {@code toString()}.
	 * 
	 * @param separator separator used between elements
	 * @return a string of the String representation of the elements.
	 */
	public String join(String separator){
		return Joiner.with(separator).join(enumerable);
    }

}
