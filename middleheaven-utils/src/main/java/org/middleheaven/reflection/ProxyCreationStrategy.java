package org.middleheaven.reflection;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;


public interface ProxyCreationStrategy {

    /**
     * 
     * @param facadeClass
     * @param handler
     * @param constructorArgs
     * @return
     */
	public <T> T proxyType (Class<T> facadeClass , ProxyHandler handler, Sequence<Object> constructorArgs);
	
	/**
	 * Applies a given interface to an object. The object can be invoke as if it implemented the interface
	 * @param delegationTarget
	 * @param proxyInterface
	 * @return
	 */
	public <I> I proxyObject(Object delegationTarget , Class<I> proxyInterface);
	
	/**
	 * Determine the real class type. 
	 * Removes all proxies created by this strategy and determines the real class.
	 * @param type
	 * @return
	 */
	public ReflectedClass<?> getRealType(ReflectedClass<?> type);
	
	public default <I> I proxyObject (Object delegationTarget ,  ProxyHandler handler , Class<I> proxyInterface){
		return proxyObject(delegationTarget, handler, proxyInterface, Sequences.empty());
	}
	
	/**
	 * 
	 * @param <I>
	 * @param delegationTarget
	 * @param handler
	 * @param proxyInterface
	 * @return an object that is instanceof {@code delegationTarget} and {@code proxyInterface}. Also this object implements {@code WrapperProxy} interface
	 */
	public <I> I proxyObject (Object delegationTarget ,  ProxyHandler handler , Class<I> proxyInterface , Sequence<Class<?>> adicionalInterfaces);
	
	public <T> T proxyType ( Class<?> facadeType, ProxyHandler handler , Class<T> proxyInterface  , Sequence<Class<?>> adicionalInterfaces);
	
	/**
	 * 
	 * @param type
	 * @return {@code true} if the type was been altered.
	 */
	public boolean isEnhanced(Class<?> type);
}
