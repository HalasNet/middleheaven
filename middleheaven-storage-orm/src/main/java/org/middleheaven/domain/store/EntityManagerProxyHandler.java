/**
 * 
 */
package org.middleheaven.domain.store;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.MethodDelegator;
import org.middleheaven.reflection.ProxyHandler;

/**
 * 
 */
public class EntityManagerProxyHandler implements ProxyHandler {

	private MetaBeanEntityInstance instance;

	/**
	 * Constructor.
	 * @param p
	 */
	public EntityManagerProxyHandler(MetaBeanEntityInstance instance) {
		this.instance = instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(Object self, Sequence<Object> args, MethodDelegator delegator)
			throws Throwable {

			if (delegator.hasSuper()){
				String name= delegator.getName();
				
				if(name.startsWith("get")) {
					return instance.getBean().get(name.substring(3).toLowerCase());
				} else if(name.startsWith("is")) {
					return instance.getBean().get(name.substring(2).toLowerCase());
				} else if(name.startsWith("set") && !args.isEmpty()) {
					instance.getBean().set(name.substring(3).toLowerCase(), args.get(0));
					return null;
				} else if(name.equals("toString")) {
					return instance.getBean().toString();
				}else {
					return delegator.invokeSuper(self, args);  // execute the original method.
				}			
			
			} else {
				return delegator.invoke(instance, args);
			}
			
		
	}

}
