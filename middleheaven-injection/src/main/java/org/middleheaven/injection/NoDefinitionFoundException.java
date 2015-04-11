/**
 * 
 */
package org.middleheaven.injection;

/**
 * 
 */
public class NoDefinitionFoundException extends RuntimeException {

	/**
	 * Constructor.
	 * @param name
	 */
	public NoDefinitionFoundException(String name) {
		super("No instanciation definition names "+ name + " was found. Please register a instantiation definitions before trying to obtain the instance.");
	}

	private static final long serialVersionUID = -8521115759257505587L;

}
