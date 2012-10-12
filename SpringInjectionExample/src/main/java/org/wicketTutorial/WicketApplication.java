package org.wicketTutorial;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wicketTutorial.ejbBean.EnterpriseMessage;

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

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan("org.wicketTutorial.ejbBean");
		ctx.refresh();
		
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
	}
}
