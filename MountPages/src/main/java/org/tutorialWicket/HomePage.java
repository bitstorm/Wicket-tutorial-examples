package org.tutorialWicket;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.WebPage;
import org.tutorialWicket.subPackage.StatefulPackageMount;
import org.tutorialWicket.subPackage.StatelessPackageMount;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		add(new Link("statefulPageMount") {

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				//pageParameters.add("foo", "foo");
				//pageParameters.add("bar", "bar");
				
				setResponsePage(MountedPage.class, pageParameters);
			}
			
		});
		
		add(new Link("mountedPageWithPlaceholder") {

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("foo", "foo");
				
				setResponsePage(MountedPageWithPlaceholder.class);
			}
			
		});
		
    	add(new Link("statelessPackage") {

			@Override
			public void onClick() {
				setResponsePage(StatelessPackageMount.class);
			}
		});
		
		add(new Link("statefulPackage") {

			@Override
			public void onClick() {
				
				setResponsePage(StatefulPackageMount.class);
			}
		});
				
    }
}
