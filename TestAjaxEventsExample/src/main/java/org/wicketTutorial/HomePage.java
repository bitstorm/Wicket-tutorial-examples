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
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public static String INIT_VALUE = "Initial value";
	public static String OTHER_VALUE = "Other value";
	
	public HomePage(final PageParameters parameters) {
		super(parameters);

		Label label;
		add(label = new Label("label", INIT_VALUE));		
		
		label.add(new AjaxEventBehavior("click") {
			
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				getComponent().setDefaultModelObject(OTHER_VALUE);
				target.add(getComponent());
			}
		}).setOutputMarkupId(true);
    }
}
