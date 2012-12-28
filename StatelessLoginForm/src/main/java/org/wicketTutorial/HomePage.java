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
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private Label sessionType;
	private String password;
	private String username;
	
    public HomePage(final PageParameters parameters) {
		StatelessForm form = new StatelessForm("form"){
			@Override
			protected void onSubmit() {
				if("user".equals(username) && username.equals(password))
					info("Username and password are correct!");
				else
					error("Wrong username or password");
			}
		};
		
		form.add(new PasswordTextField("password"));
		form.add(new TextField("username"));		
		
		add(form.setDefaultModel(new CompoundPropertyModel(this)));
		
		add(sessionType = new Label("sessionType", Model.of("")));
		add(new FeedbackPanel("feedbackPanel"));
    }
    
    @Override
    protected void onBeforeRender() {
    	super.onBeforeRender();
    	
    	if(getSession().isTemporary())
    		sessionType.setDefaultModelObject("temporary");
    	else
    		sessionType.setDefaultModelObject("permanent");
    }
}
