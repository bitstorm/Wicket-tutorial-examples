/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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

public class RSSProducerResource extends AbstractResource {

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
	
	public static SyndFeed getFeed() {
		SyndFeed feed = new SyndFeedImpl();
		
		feed.setFeedType("rss_2.0");
        feed.setTitle("My test with RSS");
        feed.setLink("http://mycompanysite.com/rss");
        feed.setDescription("Don't mind. It's just a test.");    
        
		return feed;
	}
}
