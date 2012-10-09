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
package org.wicketTutorial.behavior;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class DisableComponentListener extends AjaxCallListener {
	private static PackageResourceReference customScriptReference = 
			new PackageResourceReference(DisableComponentListener.class, "moveHiderAndIndicator.js");
	
	private static PackageResourceReference jqueryUiPositionRef = 
			new PackageResourceReference(DisableComponentListener.class, "jquery-ui-position.min.js");
	
	private static PackageResourceReference indicatorReference = 
			new PackageResourceReference(DisableComponentListener.class, "ajax-loader.gif");
	
	private Component targetComponent;
	
	public DisableComponentListener(Component targetComponent){
		this.targetComponent = targetComponent;
	}
	
	@Override
	public CharSequence getBeforeHandler(Component component) {	
		CharSequence indicatorUrl = getIndicatorUrl(component);
		return ";DisableComponentListener.disableElement('" + targetComponent.getMarkupId() + "'," +
				"'" + indicatorUrl + "');";
	}

	@Override
	public CharSequence getCompleteHandler(Component component) {
		return ";DisableComponentListener.hideComponent('" + targetComponent.getMarkupId() + "');";
	}
	
	protected CharSequence getIndicatorUrl(Component component) {
		return component.urlFor(indicatorReference, null);
	}
	
	@Override
	public void renderHead(Component component, IHeaderResponse response) {	
		ResourceReference jqueryReference =
				Application.get().getJavaScriptLibrarySettings().getJQueryReference();
		
		response.render(JavaScriptHeaderItem.forReference(jqueryReference));
		response.render(JavaScriptHeaderItem.forReference(jqueryUiPositionRef));
		response.render(JavaScriptHeaderItem.forReference(customScriptReference));
	}
}
