package org.wicketTutorial;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		setHeaderResponseDecorator(new JavaScriptToBucketResponseDecorator("footer-container"));
	}
	
	/**
     * Decorates an original {@link org.apache.wicket.markup.head.IHeaderResponse} and renders all javascript items
     * (JavaScriptHeaderItem), to a specific container in the page.
     */
    static class JavaScriptToBucketResponseDecorator implements IHeaderResponseDecorator 
    {

        private String bucketName;

        public JavaScriptToBucketResponseDecorator(String bucketName) {
            this.bucketName = bucketName;
        }

        @Override
        public IHeaderResponse decorate(IHeaderResponse response) {
            return new JavaScriptFilteredIntoFooterHeaderResponse(response, bucketName);
        }

    }
}
