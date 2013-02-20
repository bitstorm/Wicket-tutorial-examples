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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class PersonListDetails extends WebPage {
	private Form form;
	private DropDownChoice<Person> personsList;
	
	public PersonListDetails(){
		Model<Person> listModel = new Model<Person>();
		ChoiceRenderer<Person> personRender = new ChoiceRenderer<Person>(){
			@Override
			public Object getDisplayValue(Person person) {
				return person.getName() + " " + person.getSurname();
			}
		};
		
		personsList = new DropDownChoice<Person>("persons", listModel, personsPojo(),personRender){
			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}
		};
				
		add(personsList);		
		
		form = new Form("form", new CompoundPropertyModel<Person>(listModel));		
		form.add(new TextField("name"));
		form.add(new TextField("surname"));
		form.add(new TextField("address"));
		form.add(new TextField("email"));
		
		add(form);
	}
	
	private List<Person> personsPojo() {
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person("John", "Smith");
		
		person.setAddress("Corner street");
		person.setEmail("john.smith@gmail.com");
		persons.add(person);
		
		person = new Person("Jill", "Smith");
		
		person.setAddress("Main street");
		person.setEmail("jill.smith@gmail.com");
		
		persons.add(person);
		
		person = new Person("Tim", "Spencer");
		
		person.setAddress("Second street");
		person.setEmail("tim.spencer@gmail.com");
		
		persons.add(person);
		
		return persons;
	}
}
