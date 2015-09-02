package it.innoteam.giocatennis;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import it.innoteam.giocatennis.portal.HomePage;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see it.innoteam.giocatennis.Start#main(String[])
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
	
	@Override
	protected void init() {
		final IBootstrapSettings settings = new BootstrapSettings();
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.United);

        settings.setThemeProvider(themeProvider);
        settings.setDeferJavascript(true);
        
        Bootstrap.install(this, settings);
	}
}
