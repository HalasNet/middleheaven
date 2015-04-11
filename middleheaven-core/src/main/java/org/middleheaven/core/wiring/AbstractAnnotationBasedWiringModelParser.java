package org.middleheaven.core.wiring;

import java.lang.annotation.Annotation;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.ReflectedClass;
import org.middleheaven.reflection.ReflectedConstructor;
import org.middleheaven.reflection.ReflectedField;
import org.middleheaven.reflection.ReflectedMethod;
import org.middleheaven.reflection.ReflectedParameter;
import org.middleheaven.util.function.Function;


/**
 * 
 */
public abstract class AbstractAnnotationBasedWiringModelParser implements WiringModelReader {


	protected Enumerable<WiringSpecification> readParamsSpecification(ReflectedConstructor<?> constructor){
		return parseAnnotations(constructor.getParameters());
	}

	protected Enumerable<WiringSpecification> readParamsSpecification(ReflectedMethod method){
		return parseAnnotations(method.getParameters());
	}

	protected WiringSpecification readParamsSpecification(ReflectedField field){

		return parseAnnotations(field.getAnnotations(),field.getValueType());
	}

	protected final Enumerable<WiringSpecification> parseAnnotations(Sequence<ReflectedParameter> parameters){

		return parameters.asEnumerable().map( p -> parseAnnotations(p.getAnnotations(), p.getType()));
		

	}

	protected abstract WiringSpecification parseAnnotations(Sequence<Annotation> annnnotations ,ReflectedClass<?> type);





}
