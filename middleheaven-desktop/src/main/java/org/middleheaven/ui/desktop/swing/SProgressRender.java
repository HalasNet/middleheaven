package org.middleheaven.ui.desktop.swing;

import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.rendering.RenderingContext;

public class SProgressRender extends SwingUIRender {

	@Override
	protected UIComponent build(RenderingContext context, UIComponent parent,
			UIComponent component) {

		return new SProgress();
	}

}
