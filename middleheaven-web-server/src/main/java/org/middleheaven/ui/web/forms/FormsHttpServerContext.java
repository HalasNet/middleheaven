/**
 * 
 */
package org.middleheaven.ui.web.forms;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.util.List;

import org.middleheaven.aas.Subject;
import org.middleheaven.culture.Culture;
import org.middleheaven.io.repository.ManagedFileRepository;
import org.middleheaven.process.AttributeContext;
import org.middleheaven.process.web.HttpChannel;
import org.middleheaven.process.web.HttpMethod;
import org.middleheaven.process.web.HttpUrl;
import org.middleheaven.process.web.HttpUserAgent;
import org.middleheaven.process.web.server.HttpServerContext;
import org.middleheaven.process.web.server.HttpServerResponse;

/**
 * 
 */
public class FormsHttpServerContext implements WebUIServerContext {

	private HttpServerContext original;
	private WebEventsQueue webEventsQueue;
	private ViewStateStore viewStateStore;
	
	
	public FormsHttpServerContext (HttpServerContext original, WebEventsQueue webEventsQueue, ViewStateStore viewStateStore){
		this.original = original;
		this.webEventsQueue = webEventsQueue;
		this.viewStateStore = viewStateStore;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AttributeContext getAttributes() {
		return original.getAttributes();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Culture> getCultures() {
		return original.getCultures();
	}

	/**
	 * {@inheritDoc}
	 */
	public Culture getCulture() {
		return original.getCulture();
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpChannel getHttpChannel() {
		return original.getHttpChannel();
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpUrl getRequestUrl() {
		return original.getRequestUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpUrl getRefererUrl() {
		return original.getRefererUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpMethod getRequestMethod() {
		return original.getRequestMethod();
	}

	/**
	 * {@inheritDoc}
	 */
	public Subject getAuthenticatedSubject() {
		return original.getAuthenticatedSubject();
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpServerResponse getResponse() {
		return original.getResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpUserAgent getAgent() {
		return original.getAgent();
	}

	/**
	 * {@inheritDoc}
	 */
	public ManagedFileRepository getUploadRepository() {
		return original.getUploadRepository();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getContextPath() {
		return original.getContextPath();
	}

	/**
	 * {@inheritDoc}
	 */
	public InetAddress getRemoteAddress() {
		return original.getRemoteAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Writer getWriter() {
		return original.getResponse().getEntry().getContentWriter();
	}


	
}
