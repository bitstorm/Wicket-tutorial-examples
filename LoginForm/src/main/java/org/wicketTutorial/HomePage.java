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

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);
    	add(new LoginForm("loginForm"));
    }
    
    class LoginForm extends Form{
    	private TextField usernameField;
    	private PasswordTextField passwordField;
    	private Label loginStatus;
    		
    	public LoginForm(String id) {
    		super(id);
    			
    		usernameField = new TextField("username", Model.of(""));
    		passwordField = new PasswordTextField("password", Model.of(""));			
    		loginStatus = new Label("loginStatus", Model.of(""));
    			
    		add(usernameField);
    		add(passwordField);
    		add(loginStatus);
    	}

    	public final void onSubmit() {
    		String username = (String)usernameField.getDefaultModelObject();
    		String password = (String)passwordField.getDefaultModelObject();
    			
    		if(username.equals("test") && password.equals("test"))
    		     loginStatus.setDefaultModelObject("Congratulations!");
    		else
    		     loginStatus.setDefaultModelObject("Wrong username or password!");			
    	}
    }
}
