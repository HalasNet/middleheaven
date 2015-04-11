package org.middleheaven.util;

public interface StepIncrementable <T extends  StepIncrementable<T, I>, I> {

	
	public T nextBy(I increment);
	public T previousBy(I increment);

}
