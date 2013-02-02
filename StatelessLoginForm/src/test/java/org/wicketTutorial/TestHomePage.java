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

import junit.framework.Assert;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testMessageForSuccessfulLogin()
	{
		inserUsernamePassword("user", "user");	
		tester.assertInfoMessages("Username and password are correct!");
	}	
	
	@Test
	public void testMessageForFailedLogin ()
	{
		inserUsernamePassword("wrongCredential", "wrongCredential");		
		tester.assertErrorMessages("Wrong username or password");
	}
	
	protected void inserUsernamePassword(String username, String password) 
	{
		//start and render the test page
		tester.startPage(HomePage.class);
		FormTester formTester = tester.newFormTester("form");
		//set credentials
		formTester.setValue("username", username);
		formTester.setValue("password", password);		
		//submit form
		formTester.submit();
	}
}
