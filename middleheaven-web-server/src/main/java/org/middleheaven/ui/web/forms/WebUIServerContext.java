/**
 * 
 */
package org.middleheaven.ui.web.forms;

import java.io.Writer;

import org.middleheaven.process.web.server.HttpServerContext;

/**
 * 
 */
public interface WebUIServerContext extends HttpServerContext{

	/**
	 * @return
	 */
	Writer getWriter();

}
