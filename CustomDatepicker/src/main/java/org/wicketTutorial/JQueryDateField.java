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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.AttributeModifier;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.template.PackageTextTemplate;

public class JQueryDateField extends DateTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5088998263851588184L;
	private PatternDateConverter dateConverter;
	private String datePattern;
	private String countryIsoCode;
	private String jQueryCalendar;
	private CharSequence urlForIcon;
	private static final PackageResourceReference JQDatePickerRef = 
						   new PackageResourceReference(JQueryDateField.class, "JQDatePicker.js");
	 
	public String getDatePattern() {
		return datePattern;
	}
	
	public JQueryDateField(String id, IModel<Date> dateModel, String datePattern, String countryIsoCode){
		super(id, dateModel);
		
		this.datePattern = datePattern;
		this.countryIsoCode = countryIsoCode;
	}
	
	public JQueryDateField(String id, String datePattern, String countryIsoCode){
		super(id);
		
		this.datePattern = datePattern;
		this.countryIsoCode = countryIsoCode;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		setOutputMarkupId(true);
		
		PackageResourceReference resourceReference = new PackageResourceReference(getClass(), "calendar.jpg");
		
		urlForIcon = urlFor(resourceReference, new PageParameters());
		dateConverter = new PatternDateConverter(datePattern, false);
		
		add(AttributeModifier.replace("size", "12"));
	}	
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		//if component is disabled we don't have to load the JQueryUI datepicker
		if(!isEnabledInHierarchy())
			return;
		
		if(!response.wasRendered(JQDatePickerRef))
			response.render(JavaScriptHeaderItem.forReference(JQDatePickerRef, null, "JQDatePickerRef", true));
		
		String initScript = "initJQDatapicker('" + getMarkupId() +"', '" + countryIsoCode + "', '" + urlForIcon +"');";
		response.render(OnLoadHeaderItem.forScript(initScript));
	}
	
	@Override
	public <Date> IConverter<Date> getConverter(Class<Date> type) {
		return (IConverter<Date>) dateConverter;
	}	
}
