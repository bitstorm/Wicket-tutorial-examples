package org.wicketTutorial;

import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	Session.get().setAttribute("username", "tommy");
		Session.get().bind();
		
		setResponsePage(DisplaySessionParameter.class);
    }   
}
