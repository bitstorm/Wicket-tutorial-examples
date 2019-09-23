package org.wicketTutorial.restexample;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.wicketTutorial.resource.PersonsRestResource;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.restexample.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
    public void init() {
        super.init();
        
        mountResource("/personsmanager", new ResourceReference("restReference") {
            PersonsRestResource resource = new PersonsRestResource();
            @Override
            public IResource getResource() {
                return resource;
            }

        });
    }
}
