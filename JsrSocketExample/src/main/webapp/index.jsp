<%--

     Licensed to the Apache Software Foundation (ASF) under one or more
     contributor license agreements.  See the NOTICE file distributed with
     this work for additional information regarding copyright ownership.
     The ASF licenses this file to You under the Apache License, Version 2.0
     (the "License"); you may not use this file except in compliance with
     the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletContextEvent" %>
<%@page import="javax.servlet.annotation.WebListener" %>
<%@page import="javax.websocket.DeploymentException" %>
<%@page import="javax.websocket.server.*" %>
<%@page import="org.wicketTutorial.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello WebSocket</title>

        <script language="javascript" type="text/javascript">
            var wsUri = "ws://localhost:8080/WicketSocket/foo";
            var websocket = new WebSocket(wsUri);
            websocket.onopen = function(evt) { onOpen(evt) };
            websocket.onmessage = function(evt) { onMessage(evt) };
            websocket.onerror = function(evt) { onError(evt) };
			
            var websocket1 = new WebSocket(wsUri);
            websocket1.onopen = function(evt) { onOpen(evt) };
            websocket1.onmessage = function(evt) { onMessage(evt) };
            websocket1.onerror = function(evt) { onError(evt) };
            
            function init() {
                output = document.getElementById("output");
            }

            function say_hello() {
                websocket.send(nameField.value);
                writeToScreen("SENT: " + nameField.value);
            }

            function onOpen(evt) {
                writeToScreen("CONNECTED");
            }

            function onMessage(evt) {
                writeToScreen("RECEIVED: " + evt.data);
            }

            function onError(evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            }

            function writeToScreen(message) {
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;
                output.appendChild(pre);
            }

            window.addEventListener("load", init, false);
        </script>
    </head>
    <body>
<%

	
    ServerEndpointConfig configs = ServerEndpointConfig.Builder.create(EchoServer.class, "/foo").build();
	ServerContainer container = ServerContainerProvider.getServerContainer();

	out.println(container);
	
	container.addEndpoint(configs);
%>
        <h1>Getting Started with WebSocket!!</h1>

        <div style="text-align: center;">
            <form action=""> 
                <input onclick="say_hello()" value="Say Hello" type="button"> 
                <input id="nameField" name="name" value="WebSocket" type="text"><br>
            </form>
        </div>
        <div id="output"></div>
    </body>
</html>
