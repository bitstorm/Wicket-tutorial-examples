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
package org.wicketTutorial.chatExample;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.head.OnEventHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.ws.jee.AbstractWebsocketBehavior;
import org.apache.wicket.request.Url;
import org.apache.wicket.util.lang.Generics;
import org.apache.wicket.util.template.PackageTextTemplate;

public class ChatRoomPanel extends Panel {

	public ChatRoomPanel(String id) {
		super(id);	
		
		add(new AbstractWebsocketBehavior(false) {			
			@Override
			protected Url getSocketCreationURL() {				
				Url url = getContextPathURL();
				url.getSegments().add("chat");
				
				return url;
			}
			
			@Override
			public void renderHead(Component component, IHeaderResponse response){			
				super.renderHead(component, response);
				
				Map<String, Object> variables = Generics.newHashMap();
				variables.put("componentId", component.getMarkupId());
				
				PackageTextTemplate webSocketSetupTemplate =
						new PackageTextTemplate(ChatRoomPanel.class, "ChatRoomPanel.template.js");
				response.render(JavaScriptContentHeaderItem.forScript(webSocketSetupTemplate.asString(variables), null));	
			}

			@Override
			protected CharSequence onMessageJsFunction() {return "onChatMessage(evt)";}

			@Override
			protected CharSequence onOpenJsFunction() {return "joinMessage()";}

			@Override
			protected CharSequence onErrorJsFunction() {return "";}

			@Override
			protected HeaderItem wrapSocketCreationScript(
					PackageTextTemplate template, Map<String, Object> variables) {
				return OnEventHeaderItem.forScript("'joinButton'","click", template.asString(variables));
			}
			
		});
	}

}
