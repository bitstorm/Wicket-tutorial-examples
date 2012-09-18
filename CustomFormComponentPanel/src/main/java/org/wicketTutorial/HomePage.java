package org.wicketTutorial;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		Form form = new Form("form");
		Model<Double> kelvinModel = new Model<Double>();
		
		form.add(new TemperatureDegreeField("temperatureDegreeField", kelvinModel));
		form.add(new Label("kelvinValue", kelvinModel));
		
		add(form);
    }
}
