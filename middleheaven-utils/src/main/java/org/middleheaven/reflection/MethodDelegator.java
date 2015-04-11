package org.middleheaven.reflection;

import java.lang.reflect.Method;

import org.middleheaven.collections.nw.Sequence;

public interface MethodDelegator {
	
	/**
	 * 
	 * @return The underling java method being delegated.
	 */
	public Method getInvoked();
	
	/**
	 * 
	 * @return the name of the method
	 */
	public String getName();
	
	
	/**
	 * Is the delegated method implemented in the original underling non-abstract class.
	 * @return
	 */
	public boolean hasSuper();
	
	/**
	 * 
	 * @param target
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	public Object invokeSuper(Object target, Sequence<Object> args) throws Throwable;
	
	/**
	 * 
	 */
	public Object invoke(Object target, Sequence<Object> args)  throws Throwable;
	
	 
}
