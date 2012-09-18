package org.wicketTutorial;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	RegistrationInfo registrtionInfo = new RegistrationInfo();
    	registrtionInfo.setSubscribeList(true);
    	
    	Form form = new Form("form", 
    			new CompoundPropertyModel<RegistrationInfo>(registrtionInfo));		
		
    	form.add(new TextField("name"));
		form.add(new TextField("surname"));
		form.add(new TextField("address"));
		form.add(new TextField("email"));
		form.add(new CheckBox("subscribeList"));
		
		add(form);
    }
}
