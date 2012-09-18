package org.wicketTutorial;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;


public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage() {
    	add(new Link("pageWithIndexParam") {

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("foo", "foo");
				pageParameters.add("bor", "bar");
				
				setResponsePage(PageWithParameters.class, pageParameters);
			}
			
		});
    	
    	add(new Link("pageWithNamedIndexParam") {

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.set(0, "foo");
				pageParameters.set(1, "bar");
				pageParameters.add("baz", "baz");
				
				setResponsePage(PageWithParameters.class, pageParameters);
			}
			
		});
    }
      
}
