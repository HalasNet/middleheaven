/**
 * 
 */
package org.middleheaven.ui.web.forms;

import org.middleheaven.process.web.server.HttpServerContext;

/**
 * 
 */
public class SessionViewStateStore implements ViewStateStore {

	private HttpServerContext httpContext;
	
	public SessionViewStateStore (){
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(HttpServerContext httpContext) {
		this.httpContext = httpContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(HttpServerContext httpContext) {
		this.httpContext = null;
	}

}
