package org.wicketTutorial;

import java.util.regex.Pattern;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
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
	protected IConverterLocator newConverterLocator() {
		ConverterLocator defaultLocator = new ConverterLocator();
		
		defaultLocator.set(Pattern.class, new RegExpPatternConverter());
		
		return defaultLocator;
	}
}
