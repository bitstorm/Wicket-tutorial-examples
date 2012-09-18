package org.wicketTutorial;

import org.apache.wicket.Application;
import org.apache.wicket.SharedResources;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);
	
		add(new ResourceLink("rssLink", new RSSReaderResource()));
		add(new ResourceLink("globalRssLink", new SharedResourceReference("globalRSSProducer")));
    }
        
}
