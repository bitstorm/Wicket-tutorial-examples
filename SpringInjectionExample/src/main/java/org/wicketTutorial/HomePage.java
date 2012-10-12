package org.wicketTutorial;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wicketTutorial.ejbBean.EnterpriseMessage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private EnterpriseMessage enterpriseMessage;
	
    public HomePage(final PageParameters parameters) {
		super(parameters);
	
		add(new Label("message", enterpriseMessage.message));
    }
}
