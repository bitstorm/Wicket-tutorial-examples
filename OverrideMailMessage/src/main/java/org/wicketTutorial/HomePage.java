package org.wicketTutorial;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		Form form = new Form("form");
		TextField mail;
		
		form.add(mail = new TextField("email", Model.of("")));
		mail.add(EmailAddressValidator.getInstance());
		
		add(new FeedbackPanel("feedbackMessage"));
		
		add(form);
    }
}
