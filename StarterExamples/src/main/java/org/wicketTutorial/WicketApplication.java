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

import java.util.regex.Pattern;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.wicketTutorial.commons.bootstrap.BootstrapApp;
import org.wicketTutorial.commons.bootstrap.source.SourcesPage;
import org.wicketTutorial.customconverter.RegExpPatternConverter;
import de.agilecoders.wicket.webjars.WicketWebjars;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.Start#main(String[])
 */
public class WicketApplication extends BootstrapApp
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
		super.init();
		
		if (getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) 
		{			
			setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080, 8443)));
		}
		
		new BeanValidationConfiguration().configure(this);
		
		mountPage("/seecode", SourcesPage.class);
		WicketWebjars.install(this);
	}
	
	@Override
	protected IConverterLocator newConverterLocator() 
	{
		ConverterLocator defaultLocator = new ConverterLocator();
		
		defaultLocator.set(Pattern.class, new RegExpPatternConverter());
		
		return defaultLocator;
	}
}
