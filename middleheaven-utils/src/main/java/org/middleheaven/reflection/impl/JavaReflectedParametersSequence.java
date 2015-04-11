/**
 * 
 */
package org.middleheaven.reflection.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ListIterator;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.nw.AbstractSequence;
import org.middleheaven.reflection.ReflectedParameter;

/**
 * 
 */
abstract class JavaReflectedParametersSequence extends AbstractSequence<ReflectedParameter>{

	
	public static JavaReflectedParametersSequence fromMethod(Method method){
		return new MethodJavaReflectedParametersSequence(method);
	}
	
	public static <T> JavaReflectedParametersSequence fromConstructor(Constructor<T> constructor){
		return new ConstructorJavaReflectedParametersSequence(constructor);
	}
	
	public boolean equals(Object other){
		if (other instanceof JavaReflectedParametersSequence){
			return equals((JavaReflectedParametersSequence)other);
		} else if (other instanceof Enumerable){
			return other.equals(this);
		} else {
			return false;
		}
	}
	



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size() ==0;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<ReflectedParameter> iterator() {
		return Enumerables.asEnumerable(getParameterTypes())
				.map( (type, index) -> (ReflectedParameter)new JavaReflectedParameter(JavaReflectedParametersSequence.this, index.intValue()))
				.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final ReflectedParameter boundSafeGet(int index) {
		return new JavaReflectedParameter(this, index);
	}
	
	/**
	 * @return
	 */
	protected abstract Class<?>[] getParameterTypes();
	
	
	protected abstract  boolean equals(JavaReflectedParametersSequence other);
	
	/**
	 * @return
	 */
	protected abstract  Annotation[][] getParameterAnnotations();

}
