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
package org.apache.wicket.protocol.ws.jee;

import java.nio.ByteBuffer;
import java.util.Collection;

import javax.websocket.RemoteEndpoint;
import javax.websocket.RemoteEndpoint.Async;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.XmlAjaxResponse;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.protocol.ws.api.IWebSocketRequestHandler;
import org.apache.wicket.request.ILogData;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Implementation of interface AjaxRequestTarget that allows to perform the following action via websocket:
 *  - update the component markup.
 *  - append/prepend JavaScript to the current response. 
 *  
 * @author andrea
 *
 */
public class WebsocketRequestTarget implements AjaxRequestTarget, IWebSocketRequestHandler {
	
	private final Page page;
	private final XmlAjaxResponse ajaxResponse;
	private final Response response;
	private final RemoteEndpoint.Async remote;	
	
	public WebsocketRequestTarget(Page page, Response response, Async remote) {
		super();
		this.page = page;
		this.response = response;
		this.remote = remote;
		
		this.ajaxResponse = new XmlAjaxResponse(page)
		{
			@Override
			protected void fireOnAfterRespondListeners(Response response)
			{
			}

			@Override
			protected void fireOnBeforeRespondListeners()
			{
			}
		};
	}

	@Override
	public Integer getPageId() {
		return page.getPageId();
	}

	@Override
	public boolean isPageInstanceCreated() {
		return true;
	}

	@Override
	public Integer getRenderCount() {
		return page.getRenderCount();
	}

	@Override
	public Class<? extends IRequestablePage> getPageClass() {
		return page.getPageClass();
	}

	@Override
	public PageParameters getPageParameters() {
		return page.getPageParameters();
	}

	@Override
	public void respond(IRequestCycle requestCycle) {		
	}

	@Override
	public void detach(IRequestCycle requestCycle) {
	}

	@Override
	public ILogData getLogData() {
		return null;
	}

	public void add(Component component) {
		add(component, component.getMarkupId());
	}
	
	@Override
	public void add(Component component, String markupId) {
		ajaxResponse.add(component, markupId);
	}

	@Override
	public void add(Component... components) {
		for (Component component : components) {
			add(component);
		}
	}

	@Override
	public void addChildren(MarkupContainer parent, Class<?> childCriteria) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener(IListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void appendJavaScript(CharSequence javascript) {
		ajaxResponse.appendJavaScript(javascript);
	}

	@Override
	public void prependJavaScript(CharSequence javascript) {
		ajaxResponse.prependJavaScript(javascript);
	}

	@Override
	public void registerRespondListener(ITargetRespondListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<? extends Component> getComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void focusComponent(Component component) {
		// TODO Auto-generated method stub

	}

	@Override
	public IHeaderResponse getHeaderResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastFocusedElementId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getPage() {
		return page;
	}
	
	public void writeToResponse(){		
		ajaxResponse.writeTo(response, "UTF-8");				
	}

	@Override
	public void push(CharSequence message) {		
		remote.sendText(message.toString());		
	}

	@Override
	public void push(byte[] message, int offset, int length) {
		ByteBuffer dataBuffer = ByteBuffer.allocate(length);
		dataBuffer.put(message, offset, length);
		
		remote.sendBinary(dataBuffer);			
	}
	
	public void executeJavaScript(String javaScriptCode){
		push("<execute-javascript>\n" + javaScriptCode 
				+ "\n</execute-javascript>");
	}
}
