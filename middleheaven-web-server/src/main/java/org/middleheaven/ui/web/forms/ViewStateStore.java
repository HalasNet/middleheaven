/**
 * 
 */
package org.middleheaven.ui.web.forms;

import org.middleheaven.process.web.server.HttpServerContext;

/**
 * 
 */
public interface ViewStateStore {

	/**
	 * @param httpContext
	 */
	void read(HttpServerContext httpContext);

	/**
	 * @param httpContext
	 */
	void write(HttpServerContext httpContext);

}
