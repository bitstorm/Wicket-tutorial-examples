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
package org.wicketTutorial.rolestrategy;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.wicketTutorial.commons.bootstrap.BootstrapInitializer;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.rolestrategy.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication{    		
	@Override
	public Class<? extends BootstrapBasePage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
	{
		return BasicAuthenticationSession.class;
	}

	@Override
	protected Class<? extends BootstrapBasePage> getSignInPageClass()
	{
		return SignInPage.class;
	}

	@Override
	public void init()
	{
		BootstrapInitializer.init(this);
		getSecuritySettings()
			.setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
	}
}
