package com.epam.jwd.onlinetraining.controller.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class WelcomeUserTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(WelcomeUserTag.class);

    @Override
    public int doStartTag() throws JspException {
        printTextOut("hello from custom tag");
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    private void printTextOut(String text){
        JspWriter out = pageContext.getOut();
        try {
            out.write(text);
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }
}
