package org.tutorialWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class MountedPage extends WebPage {
	private int index = 0;
	public MountedPage() {
		super();
		
	}

	public MountedPage(PageParameters parameters) {
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
