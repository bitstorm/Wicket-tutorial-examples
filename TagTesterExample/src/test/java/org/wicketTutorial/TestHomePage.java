package org.wicketTutorial;

import java.util.List;

import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
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
	public void homePageMarkupTest()
	{
		//start and render the test page
		tester.startPage(HomePage.class);
		//retrieve response markup
		String responseTxt = tester.getLastResponse().getDocument();
		
		TagTester tagTester = TagTester.createTagByAttribute(responseTxt, "class", "myClass"); 
		
		Assert.assertNotNull(tagTester);
		Assert.assertEquals("span", tagTester.getName());		
		
		List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, "class", "myClass", 
																	false);
		
		Assert.assertEquals(2, tagTesterList.size());
	}
}
