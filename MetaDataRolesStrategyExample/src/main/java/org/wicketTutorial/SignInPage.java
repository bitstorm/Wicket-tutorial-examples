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



import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

public class SignInPage extends WebPage {
	private String username;
	private String password;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		StatelessForm form = new StatelessForm("form"){
			@Override
			protected void onSubmit() {
				if(Strings.isEmpty(username) || Strings.isEmpty(password))
					return;
				
				boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
				
				if(authResult){
					continueToOriginalDestination();
					setResponsePage(Application.get().getHomePage());
				}else{
					error("Username and password are not equal!");
				}
			}
		};
		
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		form.add(new TextField("username"));
		form.add(new PasswordTextField("password"));
		
		form.add(new FeedbackPanel("feedbackPanel"));
		add(form);
	}
}
