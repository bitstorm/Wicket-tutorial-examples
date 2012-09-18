package org.tutorialWicket.subPackage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class StatelessPackageMount extends WebPage {

	public StatelessPackageMount(PageParameters parameters) {
		super(parameters);
		add(new StatelessLink("goHome") {

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
			
		});
	}
	
}
