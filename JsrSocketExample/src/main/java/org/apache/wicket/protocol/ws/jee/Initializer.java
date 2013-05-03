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

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.protocol.http.WebApplication;

public class Initializer implements IInitializer{		
	
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
	public void init(Application application){				
		WebApplication webApp = (WebApplication)application;
		ServerContainer serverContainer =  (ServerContainer) webApp.getServletContext()
											.getAttribute("javax.websocket.server.ServerContainer");
		
		ServerEndpointConfig configs = ServerEndpointConfig.Builder.create(WebsocketBehaviorEndpoint.class, 
				"/" + WebsocketBehavior.WEBSOCKET_CREATOR_URL).build();
		
		//add the current application as user property for the endpoint
		configs.getUserProperties().put("currentApplication", application);
		//define the WebsocketBehaviorsManager that will be used to pass the WebsocketBehaviorS to the endpoint
		application.setMetaData(WebsocketBehavior.WEBSOCKET_BEHAVIOR_MAP_KEY, new WebsocketBehaviorsManager());
		
		try {
			serverContainer.addEndpoint(configs);
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy(Application application) {
	}
}
