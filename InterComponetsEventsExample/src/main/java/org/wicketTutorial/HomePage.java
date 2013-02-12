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

import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);
	
		final WebMarkupContainer containerInTheMiddle = new WebMarkupContainer("containerInTheMiddle"){
			@Override
			public void onEvent(IEvent<?> event) {
				//we use session's info method to keep messages sorted by insertion order
				Session.get().info("I'm the container in the middle and I received an event.");			
			}
		};
		
		WebMarkupContainer innerContainer = new WebMarkupContainer("innerContainer"){
			@Override
			public void onEvent(IEvent<?> event) {
				//we use session's info method to keep messages sorted by insertion order
				Session.get().info("I'm the inner component and I received an event.");		
			}
		};
		
		add(containerInTheMiddle);
		containerInTheMiddle.add(innerContainer);
		FeedbackPanel feedbackPanel;
		add(feedbackPanel = new FeedbackPanel("feedbackPanel"));		
		
		StatelessLink breadthLink = new StatelessLink("breadthLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.BREADTH, "Broadcat mode: breadth.");
			}
		};
		add(breadthLink);
		StatelessLink depthLink = new StatelessLink("depthLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.DEPTH, "Broadcat mode: depth.");
			}
		};
		add(depthLink);
		StatelessLink bubbleLink = new StatelessLink("bubbleLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.BUBBLE, "Broadcat mode: bubble.");
			}
		};
		add(bubbleLink);
		StatelessLink exactLink = new StatelessLink("exactLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.EXACT, "Broadcat mode: exact.");
			}
		};
		add(exactLink);
    }
    
    @Override
    public void onEvent(IEvent<?> event) {
    	//we use session's info method to keep messages sorted by insertion order
    	Session.get().info("I'm the page and I received an event.");    	
    }
}
