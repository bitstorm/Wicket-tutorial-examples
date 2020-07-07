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
package org.wicketTutorial.basicauth;

import org.apache.wicket.Component;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public class AuthenticatedPage extends BootstrapBasePage {
	@Override
	protected void onConfigure() {
		super.onConfigure();
		AuthenticatedWebApplication app = (AuthenticatedWebApplication) AuthenticatedWebApplication.get();
		
		if(!AuthenticatedWebSession.get().isSignedIn())
			app.restartResponseAtSignInPage();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Link<Void>("goToHomePage") {

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
		});
		
		add(new Link<Void>("logOut") {

			@Override
			public void onClick() {
				AuthenticatedWebSession.get().invalidate();
				setResponsePage(getApplication().getHomePage());
			}

		});
	}
}
