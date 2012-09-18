package org.wicketTutorial;

import org.apache.wicket.Application;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	add(new Link("nonBookmarkablePage"){

			@Override
			public void onClick() {
				setResponsePage(new NonBookmarkablePage());
			}			
		});
    	add(new Link("bookmarkablePage"){

			@Override
			public void onClick() {
				setResponsePage(BookmarkablePage.class);
			}			
		});
    }
}
