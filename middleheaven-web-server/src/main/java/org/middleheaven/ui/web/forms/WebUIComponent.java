/**
 * 
 */
package org.middleheaven.ui.web.forms;

import java.io.IOException;

import org.middleheaven.ui.UIComponent;

/**
 * 
 */
public interface WebUIComponent extends UIComponent {

	/**
	 * @param context
	 */
	public void applyRequestValues(WebUIServerContext ctx);
	
	/**
	 * @param context
	 */
	void restoreViewState(WebUIServerContext context);

	/**
	 * @param context
	 */
	void applyRequestData(WebUIServerContext context);

	/**
	 * @param context
	 */
	void validate(WebUIServerContext context);

	/**
	 * @param context
	 */
	void updateModel(WebUIServerContext context);

	/**
	 * @param context
	 */
	void writeViewState(WebUIServerContext context);

	/**
	 * @param context
	 */
	void render(WebUIServerContext context) throws IOException;
}
