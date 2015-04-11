/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.NoSuchElementException;

/**
 * 
 */
public class NoSuchItem extends Element<Object> {

	
	public boolean isAfterLast(){
		return true;
	}
	
	public boolean isFound(){
		return false;
	}
	
	public Object object(){
	  throw new NoSuchElementException();
	}
}
