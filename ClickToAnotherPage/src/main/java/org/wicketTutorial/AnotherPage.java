package org.wicketTutorial;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AnotherPage extends WebPage {
	public AnotherPage(final PageParameters parameters){
		add(new Link("homePage"){

			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
			
		});
	}
}
