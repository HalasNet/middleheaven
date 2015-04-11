package org.middleheaven.ui.web.html;

import static org.middleheaven.util.SafeCastUtils.safeCast;

import java.io.IOException;

import org.middleheaven.ui.AbstractRenderedUIComponent;
import org.middleheaven.ui.CommandListener;
import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.components.UICommand;
import org.middleheaven.ui.components.UIWindowsListener;
import org.middleheaven.ui.rendering.RenderingContext;

public class GenericHtmlUIComponent extends AbstractRenderedUIComponent implements HTMLDocumentWritable, HtmlUIComponent {

	protected AbstractHtmlRender abstractHTMLRender;

	protected GenericHtmlUIComponent (){
		
	}
	
	public GenericHtmlUIComponent(UIComponent component, AbstractHtmlRender abstractHTMLRender) {
		this.abstractHTMLRender = abstractHTMLRender;
		this.type = component.getComponentType();
		this.setGID(component.getGID());
		this.setFamily(component.getFamily());
		this.visible.set(component.getVisibleProperty().get());
		this.enabled.set(component.getEnableProperty().get());
		//this.text.set(component.getTextProperty().get());
		//this.name.set(component.getNameProperty().get());
		
		if (component.isType(UICommand.class)){
			UICommand command = safeCast(component, UICommand.class).get();
			
			for (CommandListener listener : command.getCommandListeners()){
				commandListeners.add(listener);
			}
		}

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeTo(HtmlDocument doc,RenderingContext context) throws IOException{
		this.abstractHTMLRender.write(doc, context, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends UIComponent> Class<T> getComponentType() {
		return (Class<T>) this.type;
	}


}
