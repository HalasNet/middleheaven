package org.middleheaven.validation;

import java.util.Collection;
import java.util.Map;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.size.CountableEnumerableSize;
import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.quantity.math.Int;

public class LengthValidator<T> implements Validator<T> {

	
	private Integer min;
	private Integer max;

	public LengthValidator(Integer min, Integer max){
		this.min = min;
		this.max = max;
	}
	
	@Override
	public ValidationResult validate(Object obj) {
		DefaultValidationResult result = new DefaultValidationResult();	
		
		if (obj instanceof Enumerable) {
			EnumerableSize es = ((Enumerable<?>) obj).getSizeInfo();
			if (es.isInfinit()){
				if (max != null){
					result.add(MessageInvalidationReason.error(obj,"invalid.length.max" , min, max)); 
				}
			} else if (es instanceof CountableEnumerableSize){
				Int size = ((CountableEnumerableSize)es).getValue();
				if (max != null && size.compareTo(max) > 0){
					result.add(MessageInvalidationReason.error(obj,"invalid.length.max" , min, max)); 
				}
				if (min != null && size.compareTo(min) < 0){
					result.add(MessageInvalidationReason.error(obj,"invalid.length.min" , min, max)); 
				}
			}
		} else {

			int size= 0;
			
			if (obj == null) {
				size = 0;
			} else if (obj instanceof Collection) {
				size = ((Collection< ? >) obj).size();
			} else if (obj instanceof CharSequence) {
				size = ((CharSequence) obj.toString().trim()).length();	
			} else if (obj instanceof Map) {
				size = ((Map< ? , ? >) obj).size();
			} else {
				return result;
			}
			
			if (min != null && size < min ) {
				result.add(MessageInvalidationReason.error(obj,"invalid.length.min" ,  min, max));
			} else if (max != null && size > max){
				result.add(MessageInvalidationReason.error(obj,"invalid.length.max" , min, max));
			}
		}
		return result;
		
	}

}
