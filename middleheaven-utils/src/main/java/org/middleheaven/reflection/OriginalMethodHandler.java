/**
 * 
 */
package org.middleheaven.reflection;

import org.middleheaven.collections.nw.Sequence;


public class OriginalMethodHandler implements ProxyHandler {

	
	public OriginalMethodHandler(){}
	
	@Override
	public Object invoke(Object self, Sequence<Object> args, MethodDelegator delegator)throws Throwable {
		return delegator.invokeSuper(self, args);  // execute the original method.
	}
}