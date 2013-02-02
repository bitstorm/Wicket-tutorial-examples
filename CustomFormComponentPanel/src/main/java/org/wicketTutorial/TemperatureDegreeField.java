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

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TemperatureDegreeField extends FormComponentPanel<Double> {
	
	private TextField<Double> userDegree;

	public TemperatureDegreeField(String id) {
		super(id);		
	}
	
	public TemperatureDegreeField(String id, IModel<Double> model) {
		super(id, model);		
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();	
		
		AbstractReadOnlyModel<String> labelModel = new AbstractReadOnlyModel<String>() {
			@Override
			public String getObject() {
				if(getLocale().equals(Locale.US))
					return "°F";
				return "°C";
			}
		};
		
		add(new Label("mesuramentUnit", labelModel));
		add(userDegree = new TextField<Double>("registeredTemperature", new Model<Double>()));
		userDegree.setType(Double.class);
	}
	
	@Override
	protected void convertInput() {
		Double userDegreeVal = userDegree.getConvertedInput();
		Double kelvinDegree;
		
		if(userDegreeVal == null) return;
		
		if(getLocale().equals(Locale.US)){
			kelvinDegree = userDegreeVal +  459.67;
			BigDecimal bdKelvin = new BigDecimal(kelvinDegree);
			BigDecimal fraction = new BigDecimal(5).divide(new BigDecimal(9));
			
			kelvinDegree = bdKelvin.multiply(fraction).doubleValue();
		}else{
			kelvinDegree = userDegreeVal + 273.15;
		}
		
		setConvertedInput(kelvinDegree);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		Double kelvinDegree = (Double) getDefaultModelObject();		
		Double userDegreeVal = null;
		
		if(kelvinDegree == null) return;
		
		if(getLocale().equals(Locale.US)){
			BigDecimal bdKelvin = new BigDecimal(kelvinDegree);
			BigDecimal fraction = new BigDecimal(9).divide(new BigDecimal(5));
			
			kelvinDegree = bdKelvin.multiply(fraction).doubleValue();
			userDegreeVal = kelvinDegree - 459.67;
		}else{
			userDegreeVal = kelvinDegree - 273.15;
		}
		
		userDegree.setModelObject(userDegreeVal);
	}
}
