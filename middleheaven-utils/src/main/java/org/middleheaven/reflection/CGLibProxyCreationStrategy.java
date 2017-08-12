package org.middleheaven.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;



class CGLibProxyCreationStrategy extends AbstractProxyCreationStrategy{


	private static class ProxyMethodDelegator implements MethodDelegator {

		private Class<? extends Object> type;
		private Method invoked;
		private MethodProxy methodProxy;

		public ProxyMethodDelegator(Class<? extends Object> type,Method invoked, MethodProxy methodProxy) {
			this.type = type;
			this.invoked = invoked;
			this.methodProxy= methodProxy;
		}

		@Override
		public Method getInvoked() {
			return invoked;
		}

		@Override
		public String getName() {
			return invoked.getName();
		}

		@Override
		public boolean hasSuper() {
			try{
				type.getDeclaredMethod(invoked.getName(), invoked.getParameterTypes());
				return true;
			} catch (NoSuchMethodException e){
				return false;
			}
		}

		@Override
		public Object invoke(Object target, Sequence<Object>  args) throws Throwable {
			return methodProxy.invoke(target, args.toArray());
		}

		@Override
		public Object invokeSuper(Object target, Sequence<Object>  args) throws Throwable {
			return methodProxy.invokeSuper(target, args.toArray());
		}

	}
	
	private static class ProxyHandlerMethodInterceptorAdapter implements MethodInterceptor{

		private ProxyHandler handler;
		private Class<?> originalType;

		public ProxyHandlerMethodInterceptorAdapter(Class<?> originalType, ProxyHandler handler) {
			super();
			this.originalType = originalType;
			this.handler = handler;
		}

		@Override
		public Object intercept(Object proxy, Method invoked, Object[] args,MethodProxy methodProxy) throws Throwable {
			try{
				return handler.invoke(proxy, Sequences.of(args), new ProxyMethodDelegator(originalType,invoked,methodProxy));
			} catch (InvocationTargetException e){
				throw e.getTargetException();
			}
		}

	}


	@Override
	public <T> T proxyType(Class<T> facadeClass, ProxyHandler handler, Sequence<Object> constructorArgs) {

		if (facadeClass == null){
			throw new IllegalArgumentException("facadeClass is required");
		}

		if (handler == null){
			throw new IllegalArgumentException("handler is required");
		}

		if (constructorArgs.isEmpty()){
			try{
				return facadeClass.cast(Enhancer.create(
						facadeClass,
						new ProxyHandlerMethodInterceptorAdapter(facadeClass,handler)
						));
			}catch (RuntimeException e){
				throw new ReflectionException("Not possible to proxy type " + facadeClass + "." + e.getMessage());
			}
		} else {
			try { 
				Enhancer en = new Enhancer();
				en.setSuperclass(facadeClass);
				en.setCallback(new ProxyHandlerMethodInterceptorAdapter(facadeClass,handler));

				@SuppressWarnings("rawtypes")
				Class[] argumentTypes = new Class[constructorArgs.size()];
				Object[] argumentValues = new Object[constructorArgs.size()];
				for (int i =0; i < constructorArgs.size(); i++){
					Object parameter = constructorArgs.get(i);
					argumentTypes[i] = this.getRealType(parameter.getClass());
					argumentValues[i] = parameter;
				}

				return facadeClass.cast(en.create(argumentTypes, argumentValues));
			}catch (RuntimeException e){
				throw new ReflectionException("Not possible to proxy type " + facadeClass + "." + e.getMessage());
			}
		}

	}

	@Override
	public <T> T proxyType(Class<?> facadeType, ProxyHandler handler, Class<T> proxyInterface, Sequence<Class<?>> adicionalInterfaces) {
		try{

			if (facadeType.isInterface()){
				Class[] interfaces = new Class[adicionalInterfaces.size() + 2];
				interfaces[0] = facadeType;
				interfaces[1] = proxyInterface;
				
				for (Sequence.Entry<Class<?>> entry : adicionalInterfaces.entries()){
					interfaces[2 + entry.getIndex()] = entry.getValue();
				}
				
				return proxyInterface.cast(Enhancer.create(
						Object.class,
						interfaces,
						new ProxyHandlerMethodInterceptorAdapter(facadeType,handler)
						));
			} else {
				Class[] interfaces = new Class[adicionalInterfaces.size() + 1];
				interfaces[0] = proxyInterface;

				for (Sequence.Entry<Class<?>> entry : adicionalInterfaces.entries()){
					interfaces[1 + entry.getIndex()] = entry.getValue();
				}
				
				return proxyInterface.cast(Enhancer.create(
						facadeType,
						interfaces,
						new ProxyHandlerMethodInterceptorAdapter(facadeType,handler)
						));
			}

		}catch (RuntimeException e){
			throw new ReflectionException(e);
		}
	}



	@Override
	public <I> I proxyObject(final Object delegationTarget, Class<I> proxyInterface) {
		if (!proxyInterface.isInterface()){
			throw new IllegalArgumentException("Proxy must be applied with an interface");
		}

		try{
			return proxyInterface.cast(Enhancer.create(
					delegationTarget.getClass(), 
					new Class[]{proxyInterface, WrapperProxy.class},
					new ObjectDispatcher(delegationTarget)
					));
		}catch (RuntimeException e){
			throw new ReflectionException(e);
		}
	}

	private static class ObjectDispatcher implements Dispatcher{

		private Object object;
		
		public ObjectDispatcher(Object object){
			this.object = object;
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object loadObject() throws Exception {
			return object;
		}
		
	}

	@Override
	public <I> I proxyObject(Object delegationTarget, ProxyHandler handler,Class<I> proxyInterface, Sequence<Class<?>> adicionalInterfaces) {
		if (!proxyInterface.isInterface()){
			throw new IllegalArgumentException("Proxy must be applied with an interface");
		}

		if (adicionalInterfaces.isEmpty() && proxyInterface.isInstance(delegationTarget)){
			return proxyObject(delegationTarget,proxyInterface);
		} else {
			try{
				@SuppressWarnings("unchecked") Class[] newInterfaces = new Class[adicionalInterfaces.size()+1];
				newInterfaces[0] = proxyInterface;
				for (Sequence.Entry<Class<?>> entry : adicionalInterfaces.entries()){
					if (!entry.getValue().isInterface()){
						throw new IllegalArgumentException("Proxy must be applied with an interface");
					}
					newInterfaces[entry.getIndex()+1] = entry.getValue();
				}
				return proxyInterface.cast(Enhancer.create(
						delegationTarget.getClass(), 
						newInterfaces,
						new ProxyHandlerMethodInterceptorAdapter(delegationTarget.getClass(),handler))
						);
			} catch (IllegalArgumentException e){
				if (e.getMessage().equals("Superclass has no null constructors but no arguments were given")){
					throw new ReflectionException("Type " + delegationTarget.getClass() + " has no argumentless constructors and no arguments were given");
				} else {
					throw new ReflectionException(e);
				}
			}catch (RuntimeException e){
				throw new ReflectionException(e);
			}
		}



	}

	private Class<?> getRealType(Class<?> type) {
		int pos = type.getName().indexOf("$$");
		if (pos >=0){
			try {
				return Class.forName(type.getName().substring(0, pos));
			} catch (ClassNotFoundException e) {
				throw ReflectionException.manage(e);
			}
		}
		return type;
	}
	
	@Override
	public ReflectedClass<?> getRealType(ReflectedClass<?> type) {
		return  Reflector.getReflector().reflect(getRealType(type.getClass()) );
	}

	@Override
	public boolean isEnhanced(Class<?> type) {
		return Enhancer.isEnhanced(type);
	}






}
