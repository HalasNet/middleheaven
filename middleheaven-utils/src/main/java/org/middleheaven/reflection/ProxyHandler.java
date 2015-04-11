package org.middleheaven.reflection;

import org.middleheaven.collections.nw.Sequence;


public interface ProxyHandler  {

	public Object invoke(Object proxy, Sequence<Object> args, MethodDelegator delegator ) throws Throwable;
	
}
