/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.reflection.inspection.Introspector;
import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.property.PropertiesCopier;
import org.middleheaven.ui.property.Property;
import org.middleheaven.ui.rendering.RenderingContext;
import org.middleheaven.ui.rendering.UIRender;
import org.middleheaven.ui.web.forms.WebUIComponent;

/**
 * 
 */
public abstract class AbstractWebRender extends UIRender {

	private static final long serialVersionUID = -3940399387747269838L;


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UIComponent build(RenderingContext context, UIComponent parent,UIComponent component) {
		
		WebUIComponent rendered = createNewComponent(context, component);
		
		copyProperties(component, rendered);
		
		rendered.setUIParent(parent);
		
		return rendered;
	}
	/**
	 * @param component
	 * @param rendered
	 */
     protected void copyProperties(UIComponent component, UIComponent rendered) {
		
		Introspector.of(component).introspectClass().inspect().methods().match( m -> m.getReturnType().isSubTypeOf(Property.class)).each( m -> {
			
			PropertiesCopier.copyProperty((Property) m.invoke(component, Sequences.empty()), (Property) m.invoke(rendered, Sequences.empty()));

		});;
	}
     
     abstract protected  WebUIComponent createNewComponent(RenderingContext context,UIComponent component);
}
