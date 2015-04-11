/**
 * 
 */
package org.middleheaven.ui.web.tags;

import javax.servlet.jsp.JspException;

/**
 * 
 */
public class GetAttributeTag extends AbstractTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4665249493500970615L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int doStartTag() throws JspException {
		
		write(pageContext.getAttribute(name).toString());
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}
}
