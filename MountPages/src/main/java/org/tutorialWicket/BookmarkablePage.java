package org.tutorialWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BookmarkablePage extends WebPage {
	public BookmarkablePage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		setStatelessHint(true);
		
		add(new Link("goHome") {

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
			
		});
	}
}
