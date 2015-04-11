/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.components.UIContainer;
import org.middleheaven.ui.web.forms.WebUIComponent;
import org.middleheaven.ui.web.forms.WebUIServerContext;

/**
 * 
 */
public class WebContainer extends AbstractWebComponent implements  UIContainer{

	/**
	 * Constructor.
	 * @param type
	 */
	public WebContainer() {
		super(UIContainer.class);
	}

	protected WebContainer(Class< ? extends UIComponent> type){
		super(type);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void applyRequestValues(WebUIServerContext ctx) {
		for(WebUIComponent comp : this.getChildren()){
			comp.applyRequestValues(ctx);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restoreViewState(WebUIServerContext context) {
		for(WebUIComponent comp : this.getChildren()){
			comp.restoreViewState(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void applyRequestData(WebUIServerContext context) {
		for(WebUIComponent comp : this.getChildren()){
			comp.applyRequestData(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(WebUIServerContext context) {
		for(WebUIComponent comp : this.getChildren()){
			comp.validate(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateModel(WebUIServerContext context) {
		for(WebUIComponent comp : this.getChildren()){
			comp.updateModel(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeViewState(WebUIServerContext context) {
		for(WebUIComponent comp : this.getChildren()){
			comp.writeViewState(context);
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException 
	 */
	@Override
	public void render(WebUIServerContext context) throws IOException {
		
		Writer writer = context.getWriter();
		
		writer.append("<div class='").append(this.getComponentType().getSimpleName()).append("' id='").append(this.getGID()).append("' >");
		for(WebUIComponent comp : this.getChildren()){
			comp.render(context);
		}
		writer.append("</div>");
	}

}
