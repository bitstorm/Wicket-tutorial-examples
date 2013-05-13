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
package org.apache.wicket.protocol.ws.jee;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.IBehaviorListener;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.crypt.Base64;
import org.apache.wicket.util.template.PackageTextTemplate;


/**
 * 
 * The main class to add a webscoket callback method (onMessage) to a component. This class adds itself to
 * the application-scoped WebsocketBehaviorsManager and generates the JavaScript needed to open 
 * a websocket from client side.
 * 
 * @see WsBehaviorAndWebRequest
 * @see WebsocketBehaviorsManager
 */
public abstract class WebsocketBehavior extends AbstractWebsocketBehavior{
	private Component component;
	private String pageId;
	private String sessionId;
	private String behaviorId;	
	
	public static final MetaDataKey<WebsocketBehaviorsManager> WEBSOCKET_BEHAVIOR_MAP_KEY = 
			new MetaDataKey<WebsocketBehaviorsManager>(){};
	public static final String WEBSOCKET_CREATOR_URL = "websocketCreator";
	
	public WebsocketBehavior() {
		super(false);		
	}
	
	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);
		
		if(Session.get().isTemporary())
			Session.get().bind();
		
		this.component = component;
		this.pageId = component.getPage().getId();		
		this.sessionId = Session.get().getId();
		WebsocketBehaviorsManager behaviorsManager = Application.get().getMetaData(WEBSOCKET_BEHAVIOR_MAP_KEY);
		
		this.behaviorId = component.urlFor(this, IBehaviorListener.INTERFACE, null).toString();
		this.behaviorId = Base64.encodeBase64URLSafeString(this.behaviorId.getBytes());
		
		Request request = RequestCycle.get().getRequest();
		WsBehaviorAndWebRequest behavAndReq = new WsBehaviorAndWebRequest((WebRequest)request, this);
		
		behaviorsManager.putBehavior(behavAndReq, sessionId, behaviorId);											
	}
	
	protected abstract void onMessage(WebsocketRequestTarget target, String message, boolean last);
	
	@Override
	public void renderHead(Component component, IHeaderResponse response){			
		super.renderHead(component, response);
		
		final PackageResourceReference websocketDefFunctions = new 
				PackageResourceReference(WebsocketBehavior.class, "res/websocketDefFunctions.js");		
		response.render(JavaScriptHeaderItem.forReference(websocketDefFunctions));	
	}
	
	@Override
	protected HeaderItem wrapSocketCreationScript(PackageTextTemplate template, Map<String, Object> variables) {
		return OnLoadHeaderItem.forScript(template.asString(variables));		
	}
	
	@Override
	protected Url getSocketCreationURL() {		
		Url relative = getContextPathURL();
		
		relative.getSegments().add(WEBSOCKET_CREATOR_URL);						
		relative.addQueryParameter("sessionId", sessionId);
		relative.addQueryParameter("behaviorId", behaviorId);
		
		return relative;
	}
	
	@Override
	protected CharSequence onMessageJsFunction() {		
		return "onMessage(evt)";
	}

	@Override
	protected CharSequence onOpenJsFunction() {
		return "onOpen(evt)";
	}

	@Override
	protected CharSequence onErrorJsFunction() {
		return "onError(evt)";
	}	

	@Override
	public boolean getStatelessHint(Component component){
		return false;
	}

	@Override
	public boolean isTemporary(Component component) {
		return false;
	}

	protected Component getComponent() {
		return component;
	}

	protected String getPageId() {
		return pageId;
	}
}
