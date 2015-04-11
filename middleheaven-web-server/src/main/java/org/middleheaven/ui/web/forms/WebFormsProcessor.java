/**
 * 
 */
package org.middleheaven.ui.web.forms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.middleheaven.io.ManagedIOException;
import org.middleheaven.process.web.HttpProcessException;
import org.middleheaven.process.web.HttpStatusCode;
import org.middleheaven.process.web.server.AbstractHttpProcessor;
import org.middleheaven.process.web.server.HttpServerContext;
import org.middleheaven.process.web.server.Outcome;
import org.middleheaven.process.web.server.action.BasicOutcomeStatus;
import org.middleheaven.process.web.server.action.TerminalOutcome;
import org.middleheaven.ui.components.UIWindow;

/**
 * 
 */
public class WebFormsProcessor extends AbstractHttpProcessor{

	private WebPagesFactory pagesFactory;

	public WebFormsProcessor (WebPagesFactory pagesFactory){
		this.pagesFactory = pagesFactory;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Outcome doProcess(HttpServerContext httpContext) throws HttpProcessException {
		
		WebEventsQueue webEventsQueue = new WebEventsQueue();
		ViewStateStore viewStateStore = new SessionViewStateStore();
		WebUIServerContext context = new FormsHttpServerContext(httpContext, webEventsQueue, viewStateStore); 
		
		WebPage page = pagesFactory.CreatePage(httpContext);
		
		if (page == null){
			return new Outcome(BasicOutcomeStatus.NOT_FOUND, HttpStatusCode.NOT_FOUND);
		}
		// page should be stateless for reuse on diferent requests
		page.init(context); // cannot redirect - initialization : create controls if neeeded
		
		WebUIComponent window = page.renderView(context);
		
		if (window == null){
			return new Outcome(BasicOutcomeStatus.NOT_FOUND , HttpStatusCode.NOT_FOUND);
		} else if (!window.isType(UIWindow.class)) {
			return new Outcome(BasicOutcomeStatus.NOT_FOUND , HttpStatusCode.NOT_FOUND);
		} else if (!window.isRendered()){
			return new Outcome(BasicOutcomeStatus.FAILURE , HttpStatusCode.SERVICE_UNAVAILABLE);
		}
		
		viewStateStore.read(httpContext); // it may read from context
		
		// restore view state
		window.restoreViewState(context); // cannot redirect  - load previous request viewstate on controls. viewstate can be read from diferent sources
		
		// apply request values (may produce events)
		window.applyRequestData(context); // cannot redirect - update previous state with current state.
		
		// TODO processEvents  -> may redirect
		
		webEventsQueue.processEvents(context);
		
		// process validation (may produce events)
		window.validate(context); // validate data received on post
	
		// TODO processEvents  -> may redirect
		webEventsQueue.processEvents(context);
		
		window.updateModel(context);

		// TODO processEvents  -> may redirect
		webEventsQueue.processEvents(context);
		
		
		page.load(context); // do general logic: loading data from db, sorting , etc..
		
		// TODO processEvents  -> may redirect
		webEventsQueue.processEvents(context);
		
		
		// RENDER : Save State > Render HTML , Unload
		
		window.writeViewState(context); // save viewstate of controls. viewstate can be writen to diferent sources
		
		viewStateStore.write(httpContext); // it may write to context
		
		try{
			window.render(context); // to html

		}catch (IOException e){
			// TODO  handle exceptions
			throw ManagedIOException.manage(e);
		}
		
		page.unload(context); // clean up
		
		return new TerminalOutcome();
	}

}
