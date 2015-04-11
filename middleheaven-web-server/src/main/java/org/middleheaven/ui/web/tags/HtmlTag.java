/**
 * 
 */
package org.middleheaven.ui.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * 
 */
public class HtmlTag extends AbstractBodyTagSupport {


	private static final long serialVersionUID = 4366369186908022940L;

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	
	String query =null;

	public int doAfterBody() throws JspException {
		  BodyContent bc = getBodyContent();
	      // get the bc as string
	      query = bc.getString();
	      // clean up
	      bc.clearBody();
	  
	    return SKIP_BODY;
	}

	public int doEndTag() throws JspException  {
		if (query!=null){
			write("<html>");
			write(query);
			write("</html>");
		}
		query =null;
		return EVAL_PAGE;
	}

	@Override
	public void releaseState() {
		// no-op
	}
}
