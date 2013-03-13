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
package org.wicketTutorial.behaviors;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.RequestListenerInterface;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.behavior.IBehaviorListener;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.AbstractSingleSelectChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.StringValue;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class OnChangeSingleChoiceBehavior extends Behavior implements IBehaviorListener{
	private AbstractSingleSelectChoice boundComponent;
	
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		Application app = Application.get();
		ResourceReference jqReference = app.getJavaScriptLibrarySettings().getJQueryReference();
		
		response.render(JavaScriptHeaderItem.forReference(jqReference));
	}
	
	@Override
	public final void bind(final Component hostComponent){
		Args.notNull(hostComponent, "hostComponent");
		
		if (boundComponent != null){
			throw new IllegalStateException("this kind of handler cannot be attached to " +
				"multiple components; it is already attached to component " + boundComponent +
				", but component " + hostComponent + " wants to be attached too");
		}
		
		if(!(hostComponent instanceof AbstractSingleSelectChoice)){
			throw new IllegalStateException("This behavior must be attached to AbstractSingleSelectChoice" 
											 + " component, but component " + hostComponent + " is not one of those");
		}

		boundComponent = (AbstractSingleSelectChoice) hostComponent;
		
	}
	
	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);
		
		CharSequence callBackURL = getCallbackUrl();
		String separatorChar = (callBackURL.toString().indexOf('?') > -1 ? "&" : "?");
		
		String finalScript = "var isSelect = $(this).is('select');\n" +
							 "var component;\n" +	
							 "if(isSelect)\n" +
							 "	component = $(this);\n" +
							 "else \n" +
							 "	component = $(this).find('input:radio:checked');\n" +
							 "window.location.href='" + callBackURL + separatorChar + "choiceId=' + " +
							 		"component.val()";
		
		tag.put("onchange", finalScript);
	}
	
	public CharSequence getCallbackUrl(){
		if (boundComponent == null){
			throw new IllegalArgumentException(
				"Behavior must be bound to a component to create the URL");
		}

		final RequestListenerInterface rli;

		rli = IBehaviorListener.INTERFACE;

		return boundComponent.urlFor(this, rli, new PageParameters());
	}
	
	protected Object convertChoiceIdToChoice(String id){
		final List choices = boundComponent.getChoices();
		final IChoiceRenderer renderer = boundComponent.getChoiceRenderer();
		
		for (int index = 0; index < choices.size(); index++){
			final Object choice = choices.get(index);
			if (renderer.getIdValue(choice, index).equals(id)){
				return choice;
			}
		}
		return null;
	}
	
	@Override
	public void onRequest() {
		
		Request request = RequestCycle.get().getRequest();
		IRequestParameters requestParameters = request.getRequestParameters();
		StringValue choiceId = requestParameters.getParameterValue("choiceId");
		
		boundComponent.setDefaultModelObject(convertChoiceIdToChoice(choiceId.toString()));
	}
	
	protected Component getComponent() {
		return boundComponent;
	}	
}
