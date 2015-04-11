/**
 * 
 */
package org.middleheaven.ui.data;


/**
 * 
 */
public interface UIDataContainer {

	public int size();
	public UIDataFields getFields();
	public Iterable<UIDataItem> getItems();
}
