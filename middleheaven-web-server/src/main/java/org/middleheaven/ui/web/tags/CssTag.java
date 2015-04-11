/**
 * 
 */
package org.middleheaven.ui.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * 
 */
public class CssTag extends AbstractBodyTagSupport{

	private static final long serialVersionUID = 8501435774394256242L;

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
	
		pageContext.getResponse().setContentType("text/css; charset=ISO-8859-1");

		
		if (query!=null){
			write(query);
		}
		query =null;
		return EVAL_PAGE;
	}

	@Override
	public void releaseState() {
		// no-op
	}
}
