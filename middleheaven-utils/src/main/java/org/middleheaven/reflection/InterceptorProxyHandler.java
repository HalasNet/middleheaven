package org.middleheaven.reflection;

import org.middleheaven.collections.nw.Sequence;

public abstract class InterceptorProxyHandler implements ProxyHandler , WrapperProxy{

	
	private Object original;

	public InterceptorProxyHandler(Object original){
		this.original = original;
	}
	
	public Object getWrappedObject(){
		return original;
	}
	
	@Override
	public final Object invoke(Object proxy, Sequence<Object> args, MethodDelegator delegator) throws Throwable {
		if (willIntercept(proxy, args,delegator)){
			return doInstead(proxy,args, delegator);
		} else {
			return doOriginal(args, delegator);
		}
	}
	
	protected final Object doOriginal(Sequence<Object> args, MethodDelegator delegator) throws Throwable{
		return delegator.invoke(original, args);  // execute the original method.
	}
	
	protected abstract boolean willIntercept(Object proxy, Sequence<Object> args, MethodDelegator delegator) throws Throwable ;
	
	protected abstract Object doInstead(Object proxy, Sequence<Object> args, MethodDelegator delegator) throws Throwable;
}
