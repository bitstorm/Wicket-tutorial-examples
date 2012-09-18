package org.wicketTutorial;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;

public class CustomPanel extends Panel {

	public CustomPanel(String id) {
		super(id);
		PackageResourceReference resourceReference = 
	            new PackageResourceReference(getClass(), "calendar.jpg");
		add(new Image("packageResPicture", resourceReference));
	}

}
