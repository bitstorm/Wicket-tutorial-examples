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
package org.wicketTutorial.websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.page.IPageManager;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.cycle.RequestCycleContext;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.response.StringResponse;
import org.apache.wicket.session.ISessionStore;
/**
 * 
 * Endpoint class that opens websockets for WebsocketBehavior.
 *
 */
public class WebsocketBehaviorEndpoint extends Endpoint{
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		
		RemoteEndpoint.Basic remote = session.getBasicRemote();
		Map<String, Object> userProperties = config.getUserProperties();
		Application application = (Application) userProperties.get("currentApplication");
		WebsocketBehaviorsManager behaviorsManager = application.getMetaData(WebsocketBehavior.WEBSOCKET_BEHAVIOR_MAP_KEY);
		
		Map<String, List<String>> parameters = session.getRequestParameterMap();
		
		String behaviorId = parameters.get("behaviorId").get(0);
		String sessionId = parameters.get("sessionId").get(0);

		WsBehaviorAndWebRequest behaviorAndReq = behaviorsManager.getAndRemoveBehavior(sessionId, behaviorId);
		
		WebsocketBehaviorListener listener = new WebsocketBehaviorListener(remote, application, 
											behaviorAndReq.getBehavior(), behaviorAndReq.getRequest());	
		session.addMessageHandler(listener);		
	}	
}

/**
 * Websocket listener class that invokes WebsocketBehavior callback method (onMessage) when the relative
 * websocket receives a textual message from client.
 * @author andrea
 *
 */
class WebsocketBehaviorListener implements MessageHandler.Partial<String>{
	
	private final RemoteEndpoint.Basic remote;
	private final WebsocketBehavior behavior;
	private final Application application;
	private final int pageId;
	private WebRequest servletWebRequest;
	
	public WebsocketBehaviorListener(Basic remote, Application application, 
									  WebsocketBehavior behavior, WebRequest servletWebRequest) {
		super();
		this.remote = remote;		
		this.behavior = behavior;
		this.application = application;
		this.pageId = Integer.parseInt(behavior.getPageId(), 10);
		this.servletWebRequest = servletWebRequest;
	}

	@Override
	public final void onMessage(String partialMessage, boolean last) {
		String test = broadcastMessage(partialMessage, last);
		
		try {
			remote.sendText(test);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final String broadcastMessage(final String message, boolean last)
	{
		
		Application oldApplication = ThreadContext.getApplication();
		org.apache.wicket.Session oldSession = ThreadContext.getSession();
		RequestCycle oldRequestCycle = ThreadContext.getRequestCycle();
		
		StringResponse response = new StringResponse();		
		
		String result = "";
		
		try
		{
			RequestCycle requestCycle;
			
			if (oldRequestCycle == null)
			{
				RequestCycleContext context = new RequestCycleContext(servletWebRequest, response,
						application.getRootRequestMapper(), application.getExceptionMapperProvider().get());

				requestCycle = application.getRequestCycleProvider().get(context);				
				ThreadContext.setRequestCycle(requestCycle);
			}
			else
			{
				requestCycle = oldRequestCycle;
			}

			ThreadContext.setApplication(application);
			
			org.apache.wicket.Session session;
			if (oldSession == null)
			{
				ISessionStore sessionStore = application.getSessionStore();
				session = sessionStore.lookup(servletWebRequest);
				ThreadContext.setSession(session);
			}
			else
			{
				session = oldSession;
			}
			
			IPageManager pageManager = session.getPageManager();
			Page page;
			
			page = (Page) pageManager.getPage(0);
			
			if(page == null)
				JOptionPane.showMessageDialog(null, session.getId());
				
			try
			{
								
				page = (Page) pageManager.getPage(pageId);
				
				WebsocketRequestTarget target = new WebsocketRequestTarget(page, response);
				
				behavior.onMessage(target, message, last);
				target.writeToResponse();
				
			}
			finally
			{
				pageManager.commitRequest();
			}
			
			result = response.toString();
			
		}
		catch (Exception x)
		{
			x.printStackTrace();
		}
		finally
		{
			try
			{
				response.close();
			}
			finally
			{
				ThreadContext.setApplication(oldApplication);
				ThreadContext.setRequestCycle(oldRequestCycle);
				ThreadContext.setSession(oldSession);
			}
		}
		return result;
	}
	
}