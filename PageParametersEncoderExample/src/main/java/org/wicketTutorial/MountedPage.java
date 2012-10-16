package org.wicketTutorial;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class MountedPage extends WebPage {

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Link("goHome"){

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}			
		});
	}
}
