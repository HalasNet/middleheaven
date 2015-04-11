/**
 * 
 */
package org.middleheaven.collections.enumerable;


class Element<T>{
	
	@SuppressWarnings("rawtypes")
    private static final Element NO_SUCH_ELEMENT = new NoSuchItem();
	
	
	@SuppressWarnings("unchecked")
	public static <U> Element<U> noSuchElement(){
		return NO_SUCH_ELEMENT;
	}
		
	
	private boolean isFound = true;
	private T object;
	
	/**
	 * Constructor.
	 * @param n
	 */
	public Element(T n) {
		this.object = n;
		this.isFound = true;
	}
	
	public Element() {
		this.isFound = false;
	}
	
	public boolean isFound(){
		return isFound;
	}
	
	public T object(){
		return object;
	}
	
	public boolean isAfterLast(){
		return false;
	}
	
}