/**
 * 
 */
package org.middleheaven.reflection.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ListIterator;

import org.middleheaven.reflection.ReflectedParameter;

/**
 * 
 */
class ConstructorJavaReflectedParametersSequence extends JavaReflectedParametersSequence{

	
	@SuppressWarnings("rawtypes")
	private Constructor constructor;

	@SuppressWarnings("rawtypes")
	public ConstructorJavaReflectedParametersSequence(Constructor constructor){
		this.constructor = constructor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getParameterTypes() {
		return constructor.getParameterTypes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equals(JavaReflectedParametersSequence other) {
		 return other instanceof ConstructorJavaReflectedParametersSequence && ((ConstructorJavaReflectedParametersSequence)other).constructor.equals(this.constructor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Annotation[][] getParameterAnnotations() {
		return constructor.getParameterAnnotations();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return constructor.getParameterCount();
	}






}
