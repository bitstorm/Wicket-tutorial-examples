package org.wicketTutorial.starter;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.wicketTutorial.starter.WicketApplication;
import org.wicketTutorial.starter.security.pages.SignInPage;

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
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(SignInPage.class);

		//assert rendered page class
		tester.assertRenderedPage(SignInPage.class);
	}
}
