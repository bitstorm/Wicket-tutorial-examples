package org.wicketTutorial;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public static String INIT_VALUE = "Initial value";
	public static String OTHER_VALUE = "Other value";
	
	public HomePage(final PageParameters parameters) {
		super(parameters);

		Label label;
		add(label = new Label("label", INIT_VALUE));		
		
		label.add(new AjaxEventBehavior("click") {
			
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				getComponent().setDefaultModelObject(OTHER_VALUE);
				target.add(getComponent());
			}
		}).setOutputMarkupId(true);
    }
}
