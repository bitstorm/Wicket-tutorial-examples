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
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketTutorial.jsr303.Person;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		setDefaultModel(new CompoundPropertyModel<Person>(new Person()));
		
		Form<Void> form = new Form<Void>("form");
		
		form.add(new TextField("name").add(new PropertyValidator()));
		form.add(new TextField("email").add(new PropertyValidator()));
		form.add(new TextField("age").add(new PropertyValidator()));
		form.add(new TextField("birthDay").add(new PropertyValidator()));
		form.add(new TextField("address.city").add(new PropertyValidator()));
		form.add(new TextField("address.street").add(new PropertyValidator()));
		form.add(new TextField("address.zipCode").add(new PropertyValidator()));
		
		form.add(new FeedbackPanel("feedback"));
		
		add(form);

    }
}
