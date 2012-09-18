package org.tutorialWicket;

import org.apache.wicket.protocol.http.WebApplication;
import org.tutorialWicket.subPackage.StatefulPackageMount;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.tutorialWicket.Start#main(String[])
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
		mountPage("/pageMount/", MountedPage.class);
		mountPage("/pageMount/#{foo}/otherSegm", MountedPageWithPlaceholder.class);
		mountPackage("/mountPackage", StatefulPackageMount.class);
	}
}
