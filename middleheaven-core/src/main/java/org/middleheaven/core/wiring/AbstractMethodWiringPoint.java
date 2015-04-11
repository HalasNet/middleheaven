/**
 * 
 */
package org.middleheaven.core.wiring;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.ReflectedMethod;

/**
 * 
 */
public class AbstractMethodWiringPoint {

	/**
	 * 
	 * @param factory
	 * @param method
	 * @param object
	 * @param paramsSpecifications a list with the parameters specifications. 
	 * @return
	 */
	protected final Object callMethodPoint(InstanceFactory factory, ReflectedMethod method, Object object, Enumerable<WiringSpecification> paramsSpecifications){
	
		Sequence<Object> params = method.getParameters().asEnumerable().zip(paramsSpecifications , (parameter,paramSpec) ->
		{
			try {
				
				WiringTarget target = new ParameterWiringTarget(parameter.getType(), method.getDeclaringClass(), object);
				
				Object value = factory.getInstance(WiringQuery.search(paramSpec).on(target));
				
				if (value == null && paramSpec.isRequired()){
					throw new BindingException("Can not find an instance of " + paramSpec.getContract() + " to bind with parameter of index " + parameter.getIndex() +  
							" in method " + method.getDeclaringClass().getName() + "." + method.getName());
				}
				
				return value;
			} catch (BindingNotFoundException e){
				throw new BindingException("Can not find an instance of " + paramSpec.getContract() + " to bind with parameter of index " + parameter.getIndex() +  
						" in method " + method.getDeclaringClass().getName() +"." + 
						method.getName()+ " was not found ");
			}
			
		}).into(Sink.collections()).sequence();

		return method.invoke(object, params);
	}
}
