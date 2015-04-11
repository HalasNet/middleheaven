package org.middleheaven.reflection.inspection;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.ProxyHandler;
import org.middleheaven.reflection.ReflectedProperty;
import org.middleheaven.reflection.Reflector;
import org.middleheaven.reflection.WrapperProxy;



public class ObjectInstrospector<T> {

	private T object;

	ObjectInstrospector(T object) {
		this.object = object;
	}
	
	public T copyTo(final T copy ){

		for (ReflectedProperty fa : introspectClass().inspect().properties().retriveAll()){
			fa.setValue(copy, fa.getValue(object));
		}

		return copy;
	}
	
	public T getInstance(){
		return object;
	}
	
	public boolean isNull(){
		return object==null;
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getType() {
		return (Class<T>) object.getClass();
	}
	
//	public Class<?> getRealType(){
//		return Reflector.getReflector().getRealType(getType());
//	}
	
	@SuppressWarnings("unchecked")
	public ClassIntrospector<T> introspectClass(){
		return (ClassIntrospector<T>) Introspector.of(object.getClass());
	}

	public Object unproxy() {
		return Reflector.getReflector().unproxy(this.object);
	}

	public <I> I newProxyInstance(ProxyHandler handler,Class<I> proxyInterface ) {
		return newProxyInstance(handler, proxyInterface);
	}
	
	public <I> I newProxyInstance(ProxyHandler handler,
			Class<I> proxyInterface , Sequence<Class<?>> adicionalInterfaces) {
		
		return Reflector.getReflector().proxyObject(this.object,handler, proxyInterface, adicionalInterfaces);
	}
	
	public <I> I newProxyInstance (Class<I> proxyInterface){
		return Reflector.getReflector().proxyObject(this.object, proxyInterface);
	}

	/**
	 * 
	 */
	public boolean isProxyfied() {
		return this instanceof WrapperProxy;
	}

	/**
	 * @return
	 */
	public Class<Object> getRealType() {
		return (Class<Object>) Reflector.getReflector().getRealType(introspectClass().getIntrospected()).getReflectedType();
	}


}
