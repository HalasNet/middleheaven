/**
 * 
 */
package org.middleheaven.domain.criteria;

import java.lang.reflect.Method;
import java.util.function.Function;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.MethodDelegator;
import org.middleheaven.reflection.ProxyHandler;
import org.middleheaven.reflection.Reflector;
import org.middleheaven.util.StringUtils;

/**
 * 
 */
public class FieldReadProxy<T>  {

	
	public static <X> FieldReadProxy<X> proxyOf(Class<X> type){
		
		return new FieldReadProxy<X>(type);
	}
	
	private T object;
	private PropertyReadParameters params;
	
	private FieldReadProxy(Class<T> type){
		object = Reflector.getReflector().reflect(type).newProxyInstance(new ReadProxyHandler());
	}

	public <V> PropertyReadParameters readAccessMethod (Function<T, V> function){
		params = null;
		function.apply(object);
		return params;
	}
	
	public static class PropertyReadParameters {
		
		private String name;
		private Class valueType;
		/**
		 * Obtains {@link String}.
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * Obtains {@link Class}.
		 * @return the valueType
		 */
		public Class getValueType() {
			return valueType;
		}

	}
	
	private class ReadProxyHandler implements ProxyHandler{
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object invoke(Object proxy, Sequence<Object> args, MethodDelegator delegator) throws Throwable {
			params = new PropertyReadParameters();
			Method m = delegator.getInvoked();
			if (m.getName().startsWith("is")){
				params.name = StringUtils.firstLetterToLower(m.getName().substring(2));
			} else if (m.getName().startsWith("get")){
				params.name = StringUtils.firstLetterToLower(m.getName().substring(3));
			}
			params.valueType = m.getReturnType();
			return null;
		}
	}
}
