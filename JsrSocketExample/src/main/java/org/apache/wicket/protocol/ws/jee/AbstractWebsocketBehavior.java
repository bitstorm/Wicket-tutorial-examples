package org.apache.wicket.protocol.ws.jee;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Generics;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.template.PackageTextTemplate;

public abstract class AbstractWebsocketBehavior extends Behavior {
	private final boolean isSecure;
	
	public AbstractWebsocketBehavior(boolean isSecure) {		
		this.isSecure = isSecure;
	}

	protected abstract CharSequence onMessageJsFunction();
	protected abstract CharSequence onOpenJsFunction();
	protected abstract CharSequence onErrorJsFunction();
	
	protected Url getContextPathURL(){
		RequestCycle requestCycle = RequestCycle.get();
		return Url.parse(requestCycle.getRequest().getContextPath());
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
		
		PackageTextTemplate webSocketSetupTemplate =
				new PackageTextTemplate(WebsocketBehavior.class, "res/openWebsocket.template.js");
				
		response.render(JavaScriptHeaderItem.forReference(ajaxReference));
		response.render(OnLoadHeaderItem.forScript(webSocketSetupTemplate.asString(variables)));
	}
	
	protected abstract Url getSocketCreationURL();

	protected boolean isSecure() {
		return isSecure;
	}
}
