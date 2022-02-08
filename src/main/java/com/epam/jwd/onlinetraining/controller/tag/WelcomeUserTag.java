package com.epam.jwd.onlinetraining.controller.tag;

import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class WelcomeUserTag extends TagSupport {
    public static final String ACCOUNT_SESSION_PARAM = "account";
    private static final Logger LOGGER = LogManager.getLogger(WelcomeUserTag.class);
    private String text;
    private static final String PARAGRAPH_TAGS = "<p>%s, %s</p>";

    @Override
    public int doStartTag() {
        Optional<String> accountMail = extractAccountMailFromSession();
        if (accountMail.isPresent()) {
            printTextOut(format(PARAGRAPH_TAGS, text, accountMail.get()));
            return SKIP_BODY;
        }
        return EVAL_PAGE;
    }

    private Optional<String> extractAccountMailFromSession() {
        return Optional.ofNullable(pageContext.getSession())
                .map(session -> (Account) session.getAttribute(ACCOUNT_SESSION_PARAM))
                .map(Account::getEmail);
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void printTextOut(String text) {
        JspWriter out = pageContext.getOut();
        try {
            out.write(text);
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }

    public void setText(String text) {
        this.text = text;
    }
}
