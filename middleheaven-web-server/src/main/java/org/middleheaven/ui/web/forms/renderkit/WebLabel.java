/**
 * 
 */
package org.middleheaven.ui.web.forms.renderkit;

import java.io.IOException;

import org.middleheaven.ui.components.UILabel;
import org.middleheaven.ui.web.forms.WebUIServerContext;

/**
 * 
 */
public class WebLabel extends AbstractWebComponent{

	/**
	 * Constructor.
	 * @param type
	 */
	public WebLabel() {
		super(UILabel.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void applyRequestValues(WebUIServerContext ctx) {
		//no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restoreViewState(WebUIServerContext context) {
		//no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void applyRequestData(WebUIServerContext context) {
		//no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(WebUIServerContext context) {
		//no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateModel(WebUIServerContext context) {
		//no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeViewState(WebUIServerContext context) {
		//no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(WebUIServerContext context) throws IOException {
		throw new UnsupportedOperationException("Not implememented yet");
	}

}
