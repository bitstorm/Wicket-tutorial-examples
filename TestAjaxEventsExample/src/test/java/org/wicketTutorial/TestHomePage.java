package org.wicketTutorial;

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
	public void testAjaxBehavior()
	{
		//start and render the test page
		tester.startPage(HomePage.class);
		//test if label's data object has the initial expected value
		tester.assertModelValue("label", HomePage.INIT_VALUE);		
		//simulate an AJAX "click" event
		tester.executeAjaxEvent("label", "click");
		//test if label's data object has changed as expected
		tester.assertModelValue("label", HomePage.OTHER_VALUE);
	}
}
