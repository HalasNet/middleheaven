/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.rendering.RenderingContext;
import org.middleheaven.ui.web.forms.WebUIComponent;

/**
 * 
 */
public class WebWindowRender extends AbstractWebRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4876930228747563398L;

	/**
	 * @param context
	 * @param component
	 * @return
	 */
	protected  WebUIComponent createNewComponent(RenderingContext context,UIComponent component)
	{
		 return new WebWindow();
	}

	

}
