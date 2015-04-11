package org.middleheaven.quantity.math.structure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.middleheaven.quantity.math.Numeral;
import org.middleheaven.util.Maybe;

public class StandardMathStructuresFactory extends MathStructuresFactory {


	private Map<String, NumeralFactory> structures = new HashMap<>();
	
	public StandardMathStructuresFactory(){
		registerStructure(new IntegerNumberStructure());
		registerStructure(new RealNumberStructure());
		registerStructure(new ComplexNumberStructure());
	}
	
	public <N  extends Numeral<N , ?>> void registerStructure(NumeralFactory<N> structure){
		for (Class<?> type : structure.getNumeralStructureTypes()){
			structures.put(type.getName() ,structure );	
		}
		
	}
	
	@Override
	public  <T> T numberFor(Class<T> superclass , Object ... values) {
		
		
		NumeralFactory structure = structures.get(superclass.getName());
		
		if (structure == null){
			throw new IllegalArgumentException("No Numeral Structure exists for " + superclass.getName());
		}
		
	   Maybe<Numeral<?, ?>> maybe = structure.newInstance(superclass, values);
		
	   if (maybe.isAbsent()){
		   throw new IllegalArgumentException("No support for producing Numeral Structure " + superclass.getName() + " from " + Arrays.toString(values));
	   }
		
	   return superclass.cast(maybe.get());
		
	}



}
