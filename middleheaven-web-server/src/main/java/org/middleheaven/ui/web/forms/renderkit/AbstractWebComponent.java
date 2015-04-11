/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.ui.AbstractRenderedUIComponent;
import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.web.forms.WebUIComponent;

/**
 * 
 */
public abstract class AbstractWebComponent extends AbstractRenderedUIComponent implements WebUIComponent   {

	

	protected AbstractWebComponent(Class< ? extends UIComponent> type){
		this.type = type;
	}
	
	protected final Enumerable<WebUIComponent> getChildren() {
		return Enumerables.asEnumerable(this.getChildrenComponents()).cast(WebUIComponent.class);
	}
}
