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

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerContainerProvider;
import javax.websocket.server.ServerEndpointConfig;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
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
	 * This method performs the following configuration steps: 
	 * 
	 * 1)add a webscoket endpoint mounting it to path WebsocketBehavior.WEBSOCKET_CREATOR_URL.
	 * This endpoint is used by WebsocketBehavior to open its own websocket.
	 * 2)put a WebsocketBehaviorsManager into application metadata. This class is used to pass a WebsocketBehavior
	 * to the corresponding websocket listener.
	 * 3)add the current application as user property for the endpoint.
	 * 
	 * @see org.apache.wicket.Application#init()
	 * @see WebsocketBehavior
	 * @see WebsocketBehaviorEndpoint
	 */
	@Override
	public void init()
	{
		super.init();
		
		ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.
												create(WebsocketBehaviorEndpoint.class, WebsocketBehavior.WEBSOCKET_CREATOR_URL);
		
		ServerEndpointConfig configs = builder.build();
		ServerContainer container = ServerContainerProvider.getServerContainer();
		
		//add the current application as user property for the endpoint
		configs.getUserProperties().put("currentApplication", this);
		//define the WebsocketBehaviorsManager that will be used to pass the WebsocketBehaviorS to the endpoint
		setMetaData(WebsocketBehavior.WEBSOCKET_BEHAVIOR_MAP_KEY, new WebsocketBehaviorsManager());
		
		try {
			container.addEndpoint(configs);
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
	}
}
