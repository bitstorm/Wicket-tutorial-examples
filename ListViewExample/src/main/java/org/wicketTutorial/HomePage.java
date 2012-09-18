package org.wicketTutorial;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		List<Person> persons = Arrays.asList(new Person("John", "Smith"), new Person("Dan", "Wang"));
		
		add(new ListView<Person>("persons", persons) {
			@Override
			protected void populateItem(ListItem<Person> item) {
				item.add(new Label("fullName", new PropertyModel(item.getModel(), "fullName")));								
			}			
		});
    }
}
