/**
 * 
 */
package org.middleheaven.instance;

import java.util.HashMap;
import java.util.Map;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.domain.model.EntityModel;
import org.middleheaven.reflection.MethodDelegator;
import org.middleheaven.reflection.ProxyHandler;
import org.middleheaven.reflection.Reflector;


/**
 * 
 */
public class InstanceProxy implements ProxyHandler{

	
	private Map<String, Object> values = new HashMap<String, Object>();
	private Class<?> type;
	private EntityModel model;
	
	protected InstanceProxy(Class<?> type,EntityModel model){
		this.type = type;
		this.model = model;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(Object proxy, Sequence<Object> args, MethodDelegator delegator)
			throws Throwable {
		
		
		if (args.isEmpty()){
			if ("cast".equals(delegator.getName())){
				return type.cast(proxy);
			}
			// get
			return values.get(resolveNameFromGet(delegator.getName()));
		} else {
			if ("getFieldValue".equals(delegator.getName())){
				return values.get(args.get(0).toString());
			} else if ("setFieldValue".equals(delegator.getName())){
				return values.put(args.get(0).toString(), args.get(1));
			}
			// set
		    values.put(resolveNameFromSet(delegator.getName()), args.get(0));
		    return null;
		}
		
	}

	
	private String resolveNameFromGet(String name){
		if (name.startsWith("get")){
			return name.substring(3);
		}else if (name.startsWith("is")){
			return name.substring(2);
		} else {
			throw new IllegalArgumentException("No get method");
		}
	}
	
	
	private String resolveNameFromSet(String name){
		if (name.startsWith("set")){
			return name.substring(3);
		} else {
			throw new IllegalArgumentException("No set method");
		}
	}
}
