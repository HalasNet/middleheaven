/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import org.middleheaven.ui.SceneNavigator;
import org.middleheaven.ui.UIClient;
import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.components.UICommand;
import org.middleheaven.ui.components.UICommandSet;
import org.middleheaven.ui.components.UIForm;
import org.middleheaven.ui.components.UIImage;
import org.middleheaven.ui.components.UILabel;
import org.middleheaven.ui.components.UILayout;
import org.middleheaven.ui.components.UISecretField;
import org.middleheaven.ui.components.UISelectOne;
import org.middleheaven.ui.components.UITextField;
import org.middleheaven.ui.components.UIView;
import org.middleheaven.ui.components.UIWindow;
import org.middleheaven.ui.rendering.AbstractRenderKit;
import org.middleheaven.ui.rendering.UIRender;
import org.middleheaven.ui.rendering.UIUnitConverter;
import org.middleheaven.ui.web.html.HtmlBorderLayoutRender;
import org.middleheaven.ui.web.html.HtmlCommandButtonRender;
import org.middleheaven.ui.web.html.HtmlCommandHiperLinkRender;
import org.middleheaven.ui.web.html.HtmlCommandSetRender;
import org.middleheaven.ui.web.html.HtmlDropDownRender;
import org.middleheaven.ui.web.html.HtmlFormRender;
import org.middleheaven.ui.web.html.HtmlImageRender;
import org.middleheaven.ui.web.html.HtmlLabelRender;
import org.middleheaven.ui.web.html.HtmlLayoutRender;
import org.middleheaven.ui.web.html.HtmlSecretRender;
import org.middleheaven.ui.web.html.HtmlTabsLayoutRender;
import org.middleheaven.ui.web.html.HtmlTextInputRender;

/**
 * 
 */
public class WebUIComponentRenderKit extends AbstractRenderKit {

	private static final long serialVersionUID = 4856716123897134061L;

	
	public WebUIComponentRenderKit (){
		
		this.addRender(new WebClientRender(), UIClient.class);
		this.addRender(new WebWindowRender(), UIWindow.class);
		this.addRender(new WebViewRender(), UIView.class);
		
		
		this.addRender(new HtmlLabelRender(), UILabel.class);
		this.addRender(new HtmlImageRender(), UIImage.class);
		this.addRender(new HtmlFormRender(), UIForm.class);
		
		this.addRender(new HtmlTextInputRender(), UITextField.class);
		this.addRender(new HtmlSecretRender(), UISecretField.class);
		
		final HtmlTabsLayoutRender tabsRender = new HtmlTabsLayoutRender();
		
		this.addRender(tabsRender, UILayout.class, "tabs");
		this.addRender(tabsRender, UILayout.class, "tabs:vertical");
		this.addRender(tabsRender, UILayout.class, "tabs:horizontal");

		this.addRender( new HtmlBorderLayoutRender(), UILayout.class, "border");

		this.addRender(new HtmlLayoutRender(), UILayout.class);
		
		
		UIRender ddr = new HtmlDropDownRender();
		this.addRender(ddr, UISelectOne.class);
		this.addRender(ddr, UISelectOne.class, "dropdown");
		
		this.addRender(new HtmlCommandSetRender(), UICommandSet.class);
		
		final HtmlCommandButtonRender render = new HtmlCommandButtonRender();
		this.addRender(render, UICommand.class);
		this.addRender(render, UICommand.class, "button");
		
		this.addRender(new HtmlCommandHiperLinkRender(), UICommand.class, "link");
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UIUnitConverter getUnitConverted() {
		// TODO implement RenderKit.getUnitConverted
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SceneNavigator getSceneNavigator() {
		return new HtmlSceneNavigator();
	}
	
	private static class HtmlSceneNavigator implements SceneNavigator{

		@Override
		public void dispose(UIComponent component) {
			//no-op
			component.getVisibleProperty().set(false);
		}

		
		@Override
		public void show(UIComponent component) {
			component.getVisibleProperty().set(true);
		}
		
	}

}
