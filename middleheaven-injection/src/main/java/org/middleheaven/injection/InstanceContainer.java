/**
 * 
 */
package org.middleheaven.injection;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.injection.impl.scopes.DefaultPrototypeScope;
import org.middleheaven.injection.impl.scopes.MemorySharedScope;
import org.middleheaven.util.function.Function;


/**
 * 
 */
public class InstanceContainer  {

	private static final InstanceFactory instanciateFactory = new InstanciateFactory();
	
	private Map<String, Scope> scopes = new HashMap<String, Scope>();
	private Map<String, InstanceDefinition> definitions = new HashMap<String, InstanceDefinition>();
	private Map<String, Collection<String>> typesNamesReverseMap = new HashMap<String, Collection<String>>();
	
	public InstanceContainer (){
		registerScope(SharedScope.class, new MemorySharedScope());
		registerScope(PrototypeScope.class, new DefaultPrototypeScope());
	}
	
	public <S extends Scope> void registerScope(Class<S> type, S scope){
		scopes.put(type.getName(), scope);
	}
	

	public void registerDefinition(Class<?> type){
		registerDefinition(type.getName(), type, SharedScope.class);
	}
	

	public void registerDefinition(String name, Class<?> type){
		registerDefinition(name, type, SharedScope.class);
	}
	
	public <S extends Scope> void registerDefinition(String name, Class<?> instanceType, Class<S> scopeType){
		
		Collection<String> names = typesNamesReverseMap.get(instanceType.getName());
		if (names == null){
			names = new HashSet<String>();
			typesNamesReverseMap.put(instanceType.getName(), names);
		}
		names.add(name);
		
		InstanceDefinition def = new InstanceDefinition(name, scopeType.getName(),readModel(instanceType), instanciateFactory);
		
		definitions.put(name, def);
	}
	
	
	
	private InjectionModel readModel(Class<?> type){
		PointSetInjectionModel model = new PointSetInjectionModel();
		
		
		return model;
	}
	

	public <T> T getInstance(String name, Class<T> type){
		return type.cast(getInstance(name));
	}
	
	public Object getInstance(String name){
		return getInstance(name, new CreationContext());
	}
	
	protected Object getInstance(String name, CreationContext context){
		InstanceDefinition def = definitions.get(name);
		
		if (def == null){
			throw new NoDefinitionFoundException(name);
		}
		
		Scope scope = this.scopes.get(def.getScopeName());
		
		if (scope == null){
			throw new NoScopeDefinitionFoundException(name);
		}
		
		Object instance = scope.getInstance(name);
		
		if (instance == null){
			instance =  def.getInstanceFactory().CreateInstance(def, context, this );
		}
		
		scope.offerInstance(name, instance);
		
		return instance;
	}

	public <T> Enumerable<T> getInstances(final Class<T> type){
	   Collection<String> names = this.typesNamesReverseMap.get(type.getName());
	   if (names == null || names.isEmpty()){
		   return Enumerables.emptyEnumerable();
	   }
	   
	   return Enumerables.asEnumerable(names).map(new Function<T, String>() {

		@Override
		public T apply(String name) {
			return getInstance(name, type);
		}
	});
	}
	

	public void wireInstance(Object object){
		
	}
	
}
