package org.wicketTutorial;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {		
		Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				super.onSubmit();
				success("Username is good!");
			}
		};
    	
		TextField mail;
		
		form.add(mail = new TextField("username", Model.of("")));
		mail.add(new UsernameValidator());
		
		add(new FeedbackPanel("feedbackMessage", 
				new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
		add(new FeedbackPanel("succesMessage", 
				new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
		
		add(form);
    }
    
    class ExactErrorLevelFilter implements IFeedbackMessageFilter{
    	private int errorLevel;

		public ExactErrorLevelFilter(int errorLevel){
    		this.errorLevel = errorLevel;
    	}
    	
		public boolean accept(FeedbackMessage message) {
			return message.getLevel() == errorLevel;
		}
    	
    }
}
