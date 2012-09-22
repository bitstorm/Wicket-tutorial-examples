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

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {		
		Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				super.onSubmit();
				success("Username is good!");
			}
		};
    	
		TextField mail;
		
		form.add(mail = new TextField("username", Model.of("")));
		mail.add(new UsernameValidator());
		
		add(new FeedbackPanel("feedbackMessage", 
				new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
		add(new FeedbackPanel("succesMessage", 
				new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
		
		add(form);
    }
    
    class ExactErrorLevelFilter implements IFeedbackMessageFilter{
    	private int errorLevel;

		public ExactErrorLevelFilter(int errorLevel){
    		this.errorLevel = errorLevel;
    	}
    	
		public boolean accept(FeedbackMessage message) {
			return message.getLevel() == errorLevel;
		}
    	
    }
}
