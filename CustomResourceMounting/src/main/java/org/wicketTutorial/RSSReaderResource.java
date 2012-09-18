package org.wicketTutorial;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.AbstractResource.WriteCallback;
import org.apache.wicket.request.resource.IResource.Attributes;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class RSSReaderResource extends AbstractResource {

	@Override
	protected ResourceResponse newResourceResponse(Attributes attributes) {
		ResourceResponse resourceResponse = new ResourceResponse();
		resourceResponse.setContentType("text/xml");
		resourceResponse.setTextEncoding("utf-8");
		
		resourceResponse.setWriteCallback(new WriteCallback()
		{
			@Override
			public void writeData(Attributes attributes) throws IOException
			{
				OutputStream outputStream = attributes.getResponse().getOutputStream();
				Writer writer = new OutputStreamWriter(outputStream);
				SyndFeedOutput output = new SyndFeedOutput();
		        try {
					output.output(getFeed(), writer);
				} catch (FeedException e) {
					throw new WicketRuntimeException("Problems writing feed to response...");
				}
			}			
		});
		
		return resourceResponse;
	}
	
	private SyndFeed getFeed() {
		SyndFeed feed = new SyndFeedImpl();
		
		feed.setFeedType("rss_2.0");
        feed.setTitle("My test with RSS");
        feed.setLink("http://mycompanysite.com/rss");
        feed.setDescription("Don't mind. It's just a test.");    
        
		return feed;
	}
}
