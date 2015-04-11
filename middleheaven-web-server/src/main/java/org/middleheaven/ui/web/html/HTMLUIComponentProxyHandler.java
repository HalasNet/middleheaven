/**
 * 
 */
package org.middleheaven.ui.web.html;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.reflection.MethodDelegator;
import org.middleheaven.reflection.ProxyHandler;
import org.middleheaven.ui.CommandListener;
import org.middleheaven.ui.UIComponent;
import org.middleheaven.ui.components.UICommand;
import org.middleheaven.ui.events.UIActionEvent;
import org.middleheaven.ui.rendering.RenderingContext;

/**
 * 
 */
public class HTMLUIComponentProxyHandler implements ProxyHandler {

	private UIComponent original;
	private AbstractHtmlRender htmlRender;


	/**
	 * Constructor.
	 * @param component
	 * @param abstractHtmlRender
	 */
	public HTMLUIComponentProxyHandler(UIComponent original, AbstractHtmlRender htmlRender) {
		this.original = original;
		this.htmlRender = htmlRender;
	}


	public Object getWrappedObject(){
		return original;
	}

	@Override
	public final Object invoke(Object self, Sequence<Object> args, MethodDelegator delegator) throws Throwable {
// TODO delegar para objeto sem ter que ter properties ou equals de nomes
//		if (delegator.getName().equals("isRendered")){
//			return true;
//		} else if (delegator.getName().equals("isType")){
//			return original.isType((Class<? extends UIComponent>) args[0]);
//		} else if (delegator.getName().equals("getChildrenComponents")){
//			return original.getChildrenComponents();
//		} else if (delegator.getName().equals("getType")){
//			return original.getComponentType();
//
//		} else if (delegator.getName().equals("getUIModel")){
//			return original.getUIModel();
//
//		} else if (delegator.getName().equals("getUIParent")){
//			return parent;
//
//		} else if (delegator.getName().equals("setUIParent")){
//			this.parent = (UIComponent) args[0];
//			return null;
//		} else 
		
		if (delegator.getName().equals("isRendered")){
			return true;
		} else if (delegator.getName().equals("writeTo")){
			htmlRender.write((HtmlDocument)args.get(0), (RenderingContext) args.get(1), (UIComponent) self);
			return null;
		} else if (delegator.getName().equals("fireEvent")){
			this.fireEvent((UIActionEvent) args.get(0));
			return null;
		} else {
			return delegator.invoke(original, args);
		}
	} 

	private void fireEvent(UIActionEvent event){
		
		UICommand command = (UICommand) original;
		
		for (CommandListener listener : command.getCommandListeners()){
			listener.onCommand(event);
		}
		
	}


	protected final Object doOriginal(Sequence<Object> args, MethodDelegator delegator) throws Throwable{
		return delegator.invoke(original, args);  // execute the original method.
	}


}
