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
package org.wicketTutorial.modaldialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;
import org.apache.wicket.extensions.ajax.markup.html.modal.theme.DefaultTheme;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;

public class HomePage extends BootstrapBasePage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	super(parameters);
    	
    	final ModalDialog modal = new ModalDialog("modal");
    	modal.add(new DefaultTheme());
    	modal.closeOnClick();
    	Label label = new Label(ModalDialog.CONTENT_ID, "I'm a modal dialog!");
    	
    	modal.setContent(label);
    	
    	add(modal);
    	add(new AjaxLink<Void>("open") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.open(target);				
			}    		
		});
    }
}
