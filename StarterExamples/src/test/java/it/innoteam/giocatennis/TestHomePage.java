package it.innoteam.giocatennis;

import it.innoteam.giocatennis.security.pages.SignInPage;

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
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(SignInPage.class);

		//assert rendered page class
		tester.assertRenderedPage(SignInPage.class);
	}
}
