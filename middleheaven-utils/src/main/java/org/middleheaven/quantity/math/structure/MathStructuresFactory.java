package org.middleheaven.quantity.math.structure;

import org.middleheaven.quantity.math.Numeral;

public abstract class MathStructuresFactory {

	
	private static MathStructuresFactory currentFactory = new StandardMathStructuresFactory();
	public static MathStructuresFactory getFactory(){
		return currentFactory;
	}
	
	
	public static void setFactory(MathStructuresFactory factory){
		currentFactory = factory;
	}
	
	protected MathStructuresFactory(){};
	
	public abstract <T> T numberFor (Class<T> superclass, Object ... value);


	public <T extends Numeral<T,A>, A extends AlgebricStructure<T,A>>T promote(Numeral<?, ?> other, Class<T> targetType) {
		Class<?> originType = other.getClass();
		if (originType.equals(targetType)){
			return targetType.cast(other);
		} else {
			return numberFor(targetType, originType.toString());
		}
	}
	

}
