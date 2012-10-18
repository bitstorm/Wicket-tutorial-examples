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

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	super(parameters);

    	List<String[]> countries = loadCountriesFromCsv();
    	ListDataProvider<String[]> listDataProvider = 
    							   new ListDataProvider<String[]>(countries);
    	
    	DataView<String[]> dataView = new DataView<String[]>("rows", listDataProvider) {
			
			@Override
			protected void populateItem(Item<String[]> item) {
				String[] countriesArr = item.getModelObject();
				RepeatingView repeatingView = new RepeatingView("dataRow");
				
				for (int i = 0; i < countriesArr.length; i++){
					repeatingView.add(
							new Label(repeatingView.newChildId(), countriesArr[i]));
				}
				item.add(repeatingView);
			}
		};
		
		dataView.setItemsPerPage(15);
		
		add(dataView);
		add(new PagingNavigator("pagingNavigator", dataView));
    }
    
    private List<String[]> loadCountriesFromCsv() {		
    	InputStream countriesStream = HomePage.class.getResourceAsStream("countries.csv");    	
    	Scanner scanner = new Scanner(countriesStream);
    	List<String[]> countries = new ArrayList<String[]>();
    	
    	while(scanner.hasNext()){
    		String curLine = scanner.nextLine();
    		String[] countryData = curLine.split(";");    		
    		
    		countries.add(countryData);
    	}
    	
    	scanner.close();
    	
    	return countries;
	}
}
