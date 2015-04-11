/**
 * 
 */
package org.middleheaven.ui.web;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.process.ContextScope;
import org.middleheaven.process.web.server.HttpServerContext;
import org.middleheaven.process.web.server.filters.HttpFilter;
import org.middleheaven.process.web.server.filters.HttpFilterChain;
import org.middleheaven.util.Splitter;
import org.middleheaven.util.StringUtils;

/**
 * 
 */
public class UIEngineParamsFilter implements HttpFilter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(HttpServerContext context, HttpFilterChain chain) {


		String params = context.getAttributes().getAttribute("$ui_params", String.class);

		if (!StringUtils.isEmptyOrBlank(params)){
			Sequence<String> parts = Splitter.on('&').split(params);

			for (String part : parts){
				Sequence<String> s = Splitter.on('=').split(part);
				if (s.size() == 1) {
					context.getAttributes().setAttribute(ContextScope.REQUEST, s.get(0), null);
				} else {
					context.getAttributes().setAttribute(ContextScope.REQUEST, s.get(0), s.get(1));
				}


			}
		}
		
		chain.doChain(context);

	}

}
