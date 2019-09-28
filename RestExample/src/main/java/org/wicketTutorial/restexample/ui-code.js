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
function loadAllPersons(tableId) {
	$.ajax({
		 type: "GET",
		 contentType: "application/json;charset=utf-8",
		 url: "./personsmanager/persons",
		 async: false,
		 success: function(data){
			for(var i = 0; i < data.length; i++) {
				addTableRow(tableId, data[i]);
		    }
		}
	});
}

function addTableRow(tableId, newRow) {
	var table = document.getElementById(tableId);

	var row = table.insertRow(-1);

	var name = row.insertCell(-1);
	var email = row.insertCell(-1);
	var password = row.insertCell(-1);
	var deleteLink = row.insertCell(-1);

	var deleteAnchor = document.createElement("A");

	name.innerHTML = newRow.name;
	password.innerHTML = newRow.password;
	email.innerHTML = newRow.email;

	deleteAnchor.innerHTML = "delete";
	deleteAnchor.setAttribute("onclick",
			"deleteTableRow(this.closest('tr'))");

	deleteLink.appendChild(deleteAnchor);
}

function deleteTableRow(row) {
	 $.ajax({
		 type: "DELETE",
		 contentType: "application/json; charset=utf-8",
		 url: "./personsmanager/persons/" + row.rowIndex,
		 async: false
	 });
	var table = row.closest('table');
	table.deleteRow(row.rowIndex);
}

function saveNewPerson() {
	var personPojo = new Object();
	
	personPojo.name = $('#name').val();
	personPojo.email = $('#email').val();
	personPojo.password = $('#password').val();
	 
	 var modalWindow = $(this); 
	 
	 $.ajax({
		 type: "POST",
		 contentType: "application/json",
		 url: "./personsmanager/persons",
		 data: JSON.stringify(personPojo),
		 async: false,
		 error: function(data){
			 var dataJson = $.parseJSON(data.responseText);
			 alert(dataJson.message);
		 },
	 	 success: function(data){
	 		$('#dialog-form').modal('hide');
	 		addTableRow('personsTable', data);
	 	 }
	 });
}

$().ready(function() {
	loadAllPersons('personsTable');
});