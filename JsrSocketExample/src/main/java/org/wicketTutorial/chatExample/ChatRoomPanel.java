package org.wicketTutorial.chatExample;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.head.OnEventHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.ws.jee.AbstractWebsocketBehavior;
import org.apache.wicket.request.Url;
import org.apache.wicket.util.lang.Generics;
import org.apache.wicket.util.template.PackageTextTemplate;

public class ChatRoomPanel extends Panel {

	public ChatRoomPanel(String id) {
		super(id);	
		
		add(new AbstractWebsocketBehavior(false) {			
			@Override
			protected Url getSocketCreationURL() {				
				Url url = getContextPathURL();
				url.getSegments().add("chat");
				
				return url;
			}
			
			@Override
			public void renderHead(Component component, IHeaderResponse response){			
				super.renderHead(component, response);
				
				Map<String, Object> variables = Generics.newHashMap();
				variables.put("componentId", component.getMarkupId());
				
				PackageTextTemplate webSocketSetupTemplate =
						new PackageTextTemplate(ChatRoomPanel.class, "ChatRoomPanel.template.js");
				response.render(JavaScriptContentHeaderItem.forScript(webSocketSetupTemplate.asString(variables), null));	
			}

			@Override
			protected CharSequence onMessageJsFunction() {return "onChatMessage(evt)";}

			@Override
			protected CharSequence onOpenJsFunction() {return "joinMessage()";}

			@Override
			protected CharSequence onErrorJsFunction() {return "";}

			@Override
			protected HeaderItem wrapSocketCreationScript(
					PackageTextTemplate template, Map<String, Object> variables) {
				return OnEventHeaderItem.forScript("'joinButton'","click", template.asString(variables));
			}
			
		});
	}

}
