/**
 * 
 */
package org.middleheaven.ui.web.forms;

import org.middleheaven.process.web.server.HttpServerContext;

/**
 * 
 */
public interface WebPagesFactory {

	/**
	 * @param httpContext
	 * @return
	 */
	WebPage CreatePage(HttpServerContext httpContext);

}
