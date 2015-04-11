/**
 * 
 */
package org.middleheaven.reflection;

import java.lang.annotation.Annotation;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.util.Maybe;

/**
 * 
 */
public interface ReflectedMethod {

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @return
	 */
	public int getModifiers();

	/**
	 * @return
	 */
	public ReflectedClass<?> getDeclaringClass();

	public Object invoke(Object obj, Sequence<Object> args) throws ReflectionException;
	
	public Object invokeStatic(Sequence<Object> args) throws ReflectionException;

	/**
	 * 
	 * @return
	 */
	public Sequence<ReflectedParameter> getParameters();

	
	/**
	 * @param class1
	 * @return
	 */
	public boolean isAnnotationPresent(Class<? extends Annotation> type);

	/**
	 * @param class1
	 * @return
	 */
	public <A extends Annotation> Maybe<A> getAnnotation(Class<A> type);

	/**
	 * @return
	 */
	public ReflectedClass<?> getReturnType();

	/**
	 * @return
	 */
	public Sequence<Annotation> getAnnotations();

	/**
	 * @return
	 */
	public boolean isStatic();


}
