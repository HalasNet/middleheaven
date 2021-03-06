/**
 * 
 */
package org.middleheaven.ui.web.vaadin;

import org.middleheaven.ui.CommandListener;
import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.components.UICommand;
import org.middleheaven.ui.rendering.RenderingContext;
import org.middleheaven.util.SafeCastUtils;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.BaseTheme;

/**
 * 
 */
public class VaadinLinkButtonRender extends AbstractVaadinRender {


	private static final long serialVersionUID = -6503686577233772775L;

	public VaadinLinkButtonRender (){
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VaadinUIComponent buildVaadin(RenderingContext context, UIComponent parent,
			UIComponent component) {
		
		
		UICommand command = SafeCastUtils.safeCast(component, UICommand.class).get();

		final Button button = new Button();
		button.setStyleName(BaseTheme.BUTTON_LINK);
		
		VaadinButton vButton = new VaadinButton(button);
		vButton.setUIParent(parent);
		
		for (CommandListener c : command.getCommandListeners()){
			vButton.addCommandListener(c);
		}
		
		vButton.getNameProperty().set(command.getNameProperty().get());
		vButton.getTextProperty().set(command.getTextProperty().get());
		
		return vButton;

	}

}
