/**
 * 
 */
package org.middleheaven.ui.testui;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.MethodDelegator;
import org.middleheaven.reflection.ProxyHandler;

/**
 * 
 */
public class TestUIProxyHandler implements ProxyHandler {

	private TestUIComponent<?> original;

	/**
	 * Constructor.
	 * @param t
	 */
	public TestUIProxyHandler(TestUIComponent<?> original) {
		this.original = original;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(Object proxy, Sequence<Object>  args, MethodDelegator delegator)
			throws Throwable {
		return delegator.invoke(original, args);  // execute the original method.
	}

}
