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

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.ws.api.WebSocketRequest;
import org.apache.wicket.request.http.WebRequest;

/**
 * Utility class that wraps a WebsocketBehavior with its related WebRequest.
 * 
 * @author andrea
 *
 */
public class WsBehaviorAndWebRequest {
	private final WebRequest request;
	private final WebsocketBehavior behavior;
	
	public WsBehaviorAndWebRequest(WebRequest request,
			WebsocketBehavior behavior) {
		super();
		WicketFilter wicketFilter = ((WebApplication)Application.get()).getWicketFilter();
		
		this.request =  new WebSocketRequest(new ServletRequestCopy((HttpServletRequest) request.getContainerRequest()), 
				"");
		this.behavior = behavior;
	}		
	
	public WebRequest getRequest() {
		return request;
	}

	public WebsocketBehavior getBehavior() {
		return behavior;
	}	
	
}
