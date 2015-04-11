/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public class StringsSinkFactory implements SinkFactory<String, StringSink>{

	
	protected StringsSinkFactory(){}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public StringSink createFrom(Enumerable<String> other) {
		return new StringSink(other);
	}

}
