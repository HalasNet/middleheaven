/**
 * 
 */
package org.middleheaven.ui.property;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * 
 */
public class PropertiesCopier {

	
	public static <T extends Serializable> void copyProperty(Property<T> original, Property<T> other){
		other.set(original.get());
		for(PropertyChangeListener listener : original.propertyChangeListeners()){
			other.addListener(listener);
		}
		
	}
}
