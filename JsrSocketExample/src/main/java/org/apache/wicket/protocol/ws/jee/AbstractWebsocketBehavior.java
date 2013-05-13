package org.apache.wicket.protocol.ws.jee;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Generics;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.template.PackageTextTemplate;

public abstract class AbstractWebsocketBehavior extends Behavior {
	private final boolean isSecure;
	private final PackageTextTemplate webSocketSetupTemplate = 
			new PackageTextTemplate(WebsocketBehavior.class, "res/openWebsocket.template.js");
	
	public AbstractWebsocketBehavior(boolean isSecure) {		
		this.isSecure = isSecure;
	}

	protected abstract CharSequence onMessageJsFunction();
	protected abstract CharSequence onOpenJsFunction();
	protected abstract CharSequence onErrorJsFunction();
	
	protected Url getContextPathURL(){
		RequestCycle requestCycle = RequestCycle.get();
		Url httpUrl = Url.parse(requestCycle.getRequest().getContextPath());
		String socketProtocol = isSecure() ? "wss" : "ws";
		
		httpUrl.setProtocol(socketProtocol);
		return httpUrl;
	}

	public static CharSequence getEscapedAppName() {
		return Strings.escapeMarkup(Application.get().getApplicationKey());
	}
	
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		
		final ResourceReference ajaxReference = Application.get().getJavaScriptLibrarySettings().getWicketAjaxReference();		
		Map<String, Object> variables = Generics.newHashMap();
		String fullUrl = RequestCycle.get().
							getUrlRenderer().renderFullUrl(getSocketCreationURL());
		
		variables.put("socketUrl", fullUrl);
		variables.put("componentId", component.getMarkupId());
		variables.put("onOpen", onOpenJsFunction());
		variables.put("onMessage", onMessageJsFunction());
		variables.put("onError", onErrorJsFunction());
		
		response.render(JavaScriptHeaderItem.forReference(ajaxReference));
		response.render(wrapSocketCreationScript(webSocketSetupTemplate, variables));		
	}
	
	protected abstract Url getSocketCreationURL();
	protected abstract HeaderItem wrapSocketCreationScript(PackageTextTemplate creationTemplate, Map<String, Object> variables);
	
	protected boolean isSecure() {
		return isSecure;
	}
}
