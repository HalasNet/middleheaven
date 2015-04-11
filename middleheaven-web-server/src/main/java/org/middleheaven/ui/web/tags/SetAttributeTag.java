/**
 * 
 */
package org.middleheaven.ui.web.tags;

import javax.servlet.jsp.JspException;

/**
 * 
 */
public class SetAttributeTag extends AbstractTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4665249493500970615L;

	private String name;
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public int doStartTag() throws JspException {
		
		pageContext.setAttribute(name, value);
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}
}
