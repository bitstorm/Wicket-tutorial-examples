package org.wicketTutorial;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

public class EchoServer extends Endpoint {

    public void onOpen(Session session, EndpointConfig config) {
        final RemoteEndpoint.Basic remote = session.getBasicRemote();
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String text) {
                for(int i = 0; i < 10; i++){
	            	try {
	                    remote.sendText("Got your message (" + text + "). Thanks !");
	                } catch (IOException ioe) {                   
	                }
	            	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
    }

}