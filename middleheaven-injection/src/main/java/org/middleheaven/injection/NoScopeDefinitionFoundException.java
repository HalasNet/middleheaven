/**
 * 
 */
package org.middleheaven.injection;

/**
 * 
 */
public class NoScopeDefinitionFoundException extends RuntimeException {

	/**
	 * Constructor.
	 * @param name
	 */
	public NoScopeDefinitionFoundException(String name) {
		super("Scope named " + name + " was not found. Please register a scope definition for this name, before trying to obtain an instance from it");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5051736140644654418L;

}
