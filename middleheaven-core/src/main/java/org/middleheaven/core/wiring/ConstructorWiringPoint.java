package org.middleheaven.core.wiring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.Mergeable;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.ReflectedConstructor;
import org.middleheaven.reflection.ReflectionException;

/**
 * Implements a {@link ProducingWiringPoint} using a {@link Constructor}.
 */
public final class ConstructorWiringPoint extends AbstractProducingWiringPoint {

	private ReflectedConstructor<?> constructor;


	/**
	 * 
	 * Constructor.
	 * @param constructor the constructor to use to produce the object.
	 * @param specification the constructor wiring specification.
	 * @param paramsSpecifications the params wiring specifications.
	 */
	public ConstructorWiringPoint(ReflectedConstructor<?> constructor, WiringSpecification specification, Enumerable<WiringSpecification> paramsSpecifications) {
		super(specification , paramsSpecifications);

		if (Modifier.isPrivate(constructor.getModifiers())){
			throw new IllegalArgumentException("Constructor is private");
		}
		this.constructor = constructor;

	}

	/**
	 * Constructs the object.
	 * @param <T> the class of the constructed object.
	 * @param binder the current wiring binder.
	 * @return the constructed object.
	 */
	@SuppressWarnings("unchecked")
	public <T> T produceWith(InstanceFactory factory){
		try {
			
			Sequence<Object> params = constructor.getParameters().asEnumerable().zip(this.getParamsSpecifications() , (parameter, specs) -> {
				
				WiringTarget target = new ParameterWiringTarget(parameter.getType(),  constructor.getDeclaringClass(), null);
				
				Object value  = factory.getInstance(WiringQuery.search(specs).on(target));
				if (value == null){ // wired constructor params are always mandatory even if configured otherwise 
					throw new WiringSpecificationNotSatisfiedException(this.constructor.getDeclaringClass().getReflectedType(), specs);
				}
				return value;
				
			}).into(Sink.collections()).sequence();
			
			return (T) constructor.newInstance(params);
		} catch (BindingException e){
			throw e;
		} catch (Exception e) {
			throw ReflectionException.manage(e);
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProducingWiringPoint merge(ProducingWiringPoint other) {
		if (other ==null){
			return this;
		}

		WiringSpecification thisSpecification = this.getSpecification();
		if (thisSpecification !=null){
			thisSpecification = thisSpecification.merge(other.getSpecification());
		}
		
		Sequence<Mergeable> a = 	this.getParamsSpecifications().cast(Mergeable.class).into(Sink.collections()).sequence();
		Sequence<Mergeable>  b = 	other.getParamsSpecifications().cast(Mergeable.class).into(Sink.collections()).sequence();
		
		Sequence<Mergeable>  c  = CollectionUtils.merge(a,b);
		
		return new ConstructorWiringPoint (this.constructor, thisSpecification, Enumerables.asEnumerable(c).cast(WiringSpecification.class));
		
	}

}
