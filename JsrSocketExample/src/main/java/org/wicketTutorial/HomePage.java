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

import java.util.Date;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.ws.jee.WebsocketEventBehavior;
import org.apache.wicket.protocol.ws.jee.WebsocketRequestTarget;
import org.wicketTutorial.chatExample.ChatRoomPanel;

public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	private Label firstLabel;
	 
	public HomePage(){		
		firstLabel = new Label("label", new Date().toString());
		
		add(firstLabel);
		
		firstLabel.setOutputMarkupId(true);
		
		Link reload;
		add(reload = new Link("reload"){

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				
			}
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("href", "#");
			}
		});
		
		reload.add(new WebsocketEventBehavior("click"){
			@Override
			protected void onMessage(WebsocketRequestTarget target,
					String message, boolean last) {
			
				firstLabel.setDefaultModelObject(new Date().toString());
				
				target.add(firstLabel);				
			}
		});		
		
		WebMarkupContainer progressBar = new WebMarkupContainer("progressbar");
		progressBar.setOutputMarkupId(true);
		
		progressBar.add(new WebsocketEventBehavior("click"){
			@Override
			protected void onMessage(WebsocketRequestTarget target,
					String message, boolean last) {
				for (int i = 0; i <= 100; i++) {
					target.executeJavaScript("$('#" + getComponent().getMarkupId() +
							"').find('div').css('width','" + i + "%');");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		add(progressBar);
		
		add(new ChatRoomPanel("chat"));
	}	
}
