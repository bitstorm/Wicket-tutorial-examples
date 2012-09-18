package org.tutorialWicket.subPackage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class StatefulPackageMount extends WebPage {

	public StatefulPackageMount(PageParameters parameters) {
		super(parameters);		
	}

	public StatefulPackageMount() {
		super();		
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Link("goHome") {

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
			
		});
	}

}
