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

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class BasicAuthenticationSession extends AuthenticatedWebSession {

	private String username;

	public BasicAuthenticationSession(Request request) {
		super(request);		
	}

	@Override
	public boolean authenticate(String username, String password) {
		boolean authResult = username.equals(password);
		
		if(authResult)
			this.username = username;
		
		return authResult;
	}

	public Roles getRoles() {
		Roles resultRoles = new Roles();
		
		if(isSignedIn())
			resultRoles.add("SIGNED_IN");
		
		if(username!= null && username.equals("superuser"))
			resultRoles.add(Roles.ADMIN);
		
		return resultRoles;
	}
	
	@Override
	public void signOut() {
		super.signOut();
		username = null;
	}
}
