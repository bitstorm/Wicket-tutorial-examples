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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.DeploymentException;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerContainerProvider;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.protocol.ws.api.ServletRequestCopy;
import org.apache.wicket.request.cycle.RequestCycle;
import org.wicketTutorial.websocket.WebsocketBehavior;
import org.wicketTutorial.websocket.WebsocketBehaviorEndpoint;
import org.wicketTutorial.websocket.WebsocketBehaviorsManager;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		
		ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.
												create(WebsocketBehaviorEndpoint.class, WebsocketBehavior.WEBSOCKET_CREATOR_URL);
		
		
		ServerEndpointConfig configs = builder.build();
		ServerContainer container = ServerContainerProvider.getServerContainer();
		configs.getUserProperties().put("currentApplication", this);
		setMetaData(WebsocketBehavior.WEBSOCKET_BEHAVIOR_MAP_KEY, new WebsocketBehaviorsManager());
		
		try {
			container.addEndpoint(configs);
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
	}
}
