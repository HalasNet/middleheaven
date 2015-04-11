/**
 * 
 */
package org.middleheaven.ui.web.forms;


/**
 * 
 */
public interface WebPage {

	/**
	 * @param context
	 */
	void init(WebUIServerContext context);

	/**
	 * @param formsContext
	 */
	WebUIComponent renderView(WebUIServerContext formsContext);

	/**
	 * @param context
	 */
	void load(WebUIServerContext context);

	/**
	 * @param context
	 */
	void unload(WebUIServerContext context);
	


}
