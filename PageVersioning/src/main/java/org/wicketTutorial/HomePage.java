package org.wicketTutorial;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.util.visit.IVisit;

public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	private Label firstLabel;
	private Label secondLabel;
	
	public HomePage(){
		firstLabel = new Label("label", "First label");
		secondLabel = new Label("label", "Second label");
		
		add(firstLabel);
		
		add(new Link("reload"){
			@Override
			public void onClick() {				
				if(getPage().contains(firstLabel, true))
					getPage().replace(secondLabel);
				else
					getPage().replace(firstLabel);		
			}
		});	
		
	}	
}
