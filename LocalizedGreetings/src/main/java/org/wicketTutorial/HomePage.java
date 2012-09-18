package org.wicketTutorial;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		AbstractReadOnlyModel<String> model = new AbstractReadOnlyModel<String>() {			
			@Override
			public String getObject() {
				return getString("greetingMessage")	;			
			}
		};
    	
    	add(new Label("greetingMessage", model));
		
		List<Locale> locales = Arrays.asList(Locale.ENGLISH, Locale.CHINESE, Locale.GERMAN);
		final DropDownChoice<Locale> changeLocale = new DropDownChoice<Locale>("changeLocale", new Model<Locale>(), locales);
		
		
		StatelessForm form = new StatelessForm("form"){
			@Override
			protected void onSubmit() {
				Session.get().setLocale(changeLocale.getModelObject());
			}
		};		
		
		setStatelessHint(true);
		add(form.add(changeLocale));		
    }
}
