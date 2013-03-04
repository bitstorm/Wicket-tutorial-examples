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

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		List<Locale> locales = Arrays.asList(Locale.ENGLISH, Locale.ITALIAN, Locale.GERMAN);
		List<String> colors = Arrays.asList("green", "red", "blue", "yellow");
		
		final DropDownChoice<Locale> changeLocale = new DropDownChoice<Locale>("changeLocale", new Model<Locale>(), locales);
		
		StatelessForm form = new StatelessForm("form"){
			@Override
			protected void onSubmit() {
				Session.get().setLocale(changeLocale.getModelObject());
			}
		};		
		
		DropDownChoice<String> selectColor = new DropDownChoice<String>("selectColor", new Model<String>(), colors){
			@Override
			protected boolean localizeDisplayValues() {
				return true;
			}
		};
		
		form.add(selectColor);
		
		setStatelessHint(true);
		add(form.add(changeLocale));

    }
}
