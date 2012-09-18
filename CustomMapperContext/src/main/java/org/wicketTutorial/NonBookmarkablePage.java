package org.wicketTutorial;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class NonBookmarkablePage extends WebPage {
	public NonBookmarkablePage(){
		add(new Link("goBack"){

			@Override
			public void onClick() {
				setResponsePage(Application.get().getHomePage());
			}			
		});
	}
}
