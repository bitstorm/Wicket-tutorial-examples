package org.wicketTutorial;

import java.util.regex.Pattern;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private Pattern regExpPatter;
	private String stringToSplit;
	
    public HomePage(final PageParameters parameters) {		
    	TextField mail;
		TextField stringToSplitTxt;
		
    	Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				super.onSubmit();
				String messageResult = "Tokens for the given string and pattern:<br/>";
				String[] tokens = regExpPatter.split(stringToSplit);
				
				
				for (String token : tokens) {
					messageResult += "- " + token + "<br/>";
				}
				
				success(messageResult);
			}
		};
    	
		form.setDefaultModel(new CompoundPropertyModel(this));
		form.add(mail = new TextField("regExpPatter"));
		form.add(stringToSplitTxt = new TextField("stringToSplit"));
		add(new FeedbackPanel("feedbackMessage").setEscapeModelStrings(false));
		
		add(form);
    }
     
}
