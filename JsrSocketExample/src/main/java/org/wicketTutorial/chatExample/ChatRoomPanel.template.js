/*
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
function joinMessage(){
	var username = $('#usernameField').val();
	
	$('#joinButton').attr("disabled", "disabled");
	$('#usernameField').attr("disabled", "disabled");
	$('#sendButton').removeAttr("disabled");
	
	websocket${componentId}.send(username + ": the user has joined the chat.");
}

function onChatMessage(evt){
	var text = $('#chatHistory').val();
	$('#chatHistory').val(text + '\n' + evt.data);
}

//execute at domready
jQuery(function(){
	$('#sendButton').click(function(){
		var username = $('#usernameField').val();
		websocket${componentId}.send(username + ": " + $('#messageArea').val());
		$('#messageArea').val('');
	})}
);