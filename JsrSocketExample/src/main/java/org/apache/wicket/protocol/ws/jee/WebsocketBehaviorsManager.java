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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * The class that is used to pass behaviors to the websocket endpoint. Behaviors are stored in session relative maps which 
 * in turn are stored in ConcurrentHashMap. Behaviors are wrapped in a WsBehaviorAndWebRequest along with their relative web request.
 *
 *@see WebsocketBehavior
 *@see WsBehaviorAndWebRequest
 *@see WebsocketBehaviorEndpoint
 */
public class WebsocketBehaviorsManager {
	
	private ConcurrentHashMap<String, Map<String, WsBehaviorAndWebRequest>> behaviorsMap =
								new ConcurrentHashMap<String, Map<String,WsBehaviorAndWebRequest>>();
	
	public void putBehavior(WsBehaviorAndWebRequest behaviorAndReq, String sessionId, String behaviorId){
		if(behaviorsMap.get(sessionId) == null)
			behaviorsMap.put(sessionId, new HashMap<String,WsBehaviorAndWebRequest>());
		
		Map<String, WsBehaviorAndWebRequest> sessionRelativeMap = behaviorsMap.get(sessionId);
		sessionRelativeMap.put(behaviorId, behaviorAndReq);
	}
	
	public WsBehaviorAndWebRequest getBehavior(String sessionId, String behaviorId){
		Map<String, WsBehaviorAndWebRequest> sessionRelativeMap = behaviorsMap.get(sessionId);
		
		return sessionRelativeMap.get(behaviorId);
	}
	
	public WsBehaviorAndWebRequest removeBehavior(String sessionId, String behaviorId){
		Map<String, WsBehaviorAndWebRequest> sessionRelativeMap = behaviorsMap.get(sessionId);
		
		return sessionRelativeMap.remove(behaviorId);
	}
	
	public WsBehaviorAndWebRequest getAndRemoveBehavior(String sessionId, String behaviorId){
		WsBehaviorAndWebRequest behavior = getBehavior(sessionId, behaviorId);
		removeBehavior(sessionId, behaviorId);
		
		return behavior;
	}
}
