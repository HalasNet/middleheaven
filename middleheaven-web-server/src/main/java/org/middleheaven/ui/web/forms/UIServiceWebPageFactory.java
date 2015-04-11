/**
 * 
 */
package org.middleheaven.ui.web.forms;

import org.middleheaven.process.MapContext;
import org.middleheaven.process.web.server.HttpServerContext;
import org.middleheaven.ui.Rendering;
import org.middleheaven.ui.UIClient;
import org.middleheaven.ui.UIEnvironment;
import org.middleheaven.ui.UIEnvironmentType;
import org.middleheaven.ui.UISearch;
import org.middleheaven.ui.UIService;
import org.middleheaven.ui.binding.UIBinder;
import org.middleheaven.ui.web.forms.renderkit.WebUIComponentRenderKit;

/**
 * 
 */
public class UIServiceWebPageFactory implements WebPagesFactory {

	private final UIClient client;
	
	public UIServiceWebPageFactory(UIService uiService, UIEnvironment env, UIBinder binder){
	
		uiService.registerEnvironment(env, new WebUIComponentRenderKit() , new MapContext());

		Rendering<UIClient> rendering =  uiService.getUIClientRendering(UIEnvironmentType.BROWSER);
	
		this.client = rendering.getComponent();
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WebPage CreatePage(HttpServerContext httpContext) {
		
		return new UIClientWebPage(client);
		
	}

	
	public static class UIClientWebPage implements WebPage {

		private UIClient client;

		public UIClientWebPage (UIClient client){
			this.client = client;
		}
		
		/**
		 * @param context
		 * @return
		 */
		private String parseWindowName(HttpServerContext context) {
			return context.getRequestUrl().getFilename(true);	
		}
		
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void init(WebUIServerContext context) {
			//no-op
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public WebUIComponent renderView(WebUIServerContext formsContext) {

			String windowId = parseWindowName(formsContext);
			
			return (WebUIComponent) UISearch.absolute(client).search("#" + windowId).first();
			
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void load(WebUIServerContext context) {
			//no-op
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void unload(WebUIServerContext context) {
			client = null;
		}
		
	}
}
