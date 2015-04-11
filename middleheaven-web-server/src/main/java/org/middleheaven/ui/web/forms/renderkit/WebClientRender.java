/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.rendering.RenderingContext;
import org.middleheaven.ui.rendering.UIRender;
import org.middleheaven.ui.web.Browser;


/**
 * 
 */
public class WebClientRender extends UIRender {

	private static final long serialVersionUID = -4231998931404658522L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UIComponent build(RenderingContext context, UIComponent parent, UIComponent component) {
		Browser browser =  new Browser(context.getRenderKit().getSceneNavigator());
		
		browser.setUIParent(parent);

		return browser;
	}

}
