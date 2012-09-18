package org.wicketTutorial;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class DisplaySessionParameter extends WebPage {

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		addOrReplace(new Label("username", (String) Session.get().getAttribute("username")));
		
	}
}
