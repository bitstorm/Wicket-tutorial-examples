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
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		//onComponentTag example
		add(new Label("onComponentTag", "Test value"){
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				
				//Turn the h1 tag to a span
	            tag.setName("span");
	            //Add formatting style
	            tag.put("style", "font-weight:bold");
			}
		});
		
		//onComponentTagBody example
		add(new Label("onComponentTagBody", ""){
			@Override
			public void onComponentTagBody(MarkupStream markupStream,
					ComponentTag tag) {
				if(!isEnabled())
		               replaceComponentTagBody(markupStream, tag, "(the component is disabled)"); 
		          else    
		               super.onComponentTagBody(markupStream, tag);
			}
		}.setEnabled(false));
    }
}
