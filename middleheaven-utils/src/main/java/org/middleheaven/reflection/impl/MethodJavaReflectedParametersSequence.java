/**
 * 
 */
package org.middleheaven.reflection.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.middleheaven.reflection.ReflectedParameter;

/**
 * 
 */
class MethodJavaReflectedParametersSequence extends JavaReflectedParametersSequence{

	
	private Method method;

	public MethodJavaReflectedParametersSequence(Method method){
		this.method = method;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getParameterTypes() {
		return method.getParameterTypes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equals(JavaReflectedParametersSequence other) {
		 return other instanceof MethodJavaReflectedParametersSequence && 
				 Arrays.equals(((MethodJavaReflectedParametersSequence)other).method.getParameterTypes(), this.method.getParameterTypes());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Annotation[][] getParameterAnnotations() {
		return method.getParameterAnnotations();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return method.getParameterCount();
	}

}
