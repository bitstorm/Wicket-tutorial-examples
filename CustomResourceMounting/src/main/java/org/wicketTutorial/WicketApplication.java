package org.wicketTutorial;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		ResourceReference resourceReference = new ResourceReference("rssProducer"){
			RSSReaderResource deviceMetaResource = new RSSReaderResource();
			@Override
			public IResource getResource() {
				return deviceMetaResource;
			}};
			
		mountResource("/foo/bar", resourceReference);
		mountResource("/bar/${baz}", resourceReference);
		getSharedResources().add("globalRSSProducer", resourceReference.getResource());
		
	}
}
