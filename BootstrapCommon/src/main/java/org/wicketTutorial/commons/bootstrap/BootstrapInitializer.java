package org.wicketTutorial.commons.bootstrap;

import org.apache.wicket.Application;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;

public class BootstrapInitializer
{

	public static void init(Application application)
	{
		final IBootstrapSettings settings = new BootstrapSettings();
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.United);

        settings.setThemeProvider(themeProvider);
        settings.setDeferJavascript(true);
        
        Bootstrap.install(application, settings);
	}

}
