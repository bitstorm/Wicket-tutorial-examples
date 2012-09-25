package org.wicketTutorial;

import org.apache.wicket.Session;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.protocol.http.WebApplication;

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

		// add your configuration here
	}
	
	@Override
	public void onEvent(IEvent<?> event) {
		Session.get().info("I'm the application and I received an event.");
	}
}
