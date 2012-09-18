package org.wicketTutorial;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		Form form = new Form("form");
		List<String> fruits = Arrays.asList("apple", "strawberry", "watermelon"); 

     	form.add(new DropDownChoice<String>("fruits", new Model(null), fruits));
		add(form);
    }
}
