package org.wicketTutorial;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.StatelessLink;

public class BookmarkablePage extends WebPage {
	public BookmarkablePage(){
		setStatelessHint(true);
		add(new StatelessLink("goBack"){

			@Override
			public void onClick() {
				setResponsePage(Application.get().getHomePage());
			}			
		});
	}
}
