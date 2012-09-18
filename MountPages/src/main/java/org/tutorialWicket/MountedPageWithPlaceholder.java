package org.tutorialWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class MountedPageWithPlaceholder extends WebPage {
	public MountedPageWithPlaceholder(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		setStatelessHint(true);
		
		add(new StatelessLink("goHome") {

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
			
		});
	}
}
