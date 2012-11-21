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
/**
* Submit a given form (formId) with the given action (action)
*/
function sendForm(action, formId){

	var form = document.getElementById(formId);

	form.action = action;

	form.submit();
}

/**
* Disable delete button of a given form.
*/
function setDeleteButton(formId, idField){
    var form = document.getElementById(formId);

    if(form[idField].value == 0){
		form.deleteButton.disabled = "disabled";
	}
}

/**
* Submit a given form (formId) with the given action (action)
* Before submit asks confirm to user.
*/
function confirmActionSubmit(message, action, formId){
	if(confirm(message, formId)){
		sendForm(action, formId);
	}
}

/**
* Check if user had copiled all form's fields. Return true if all fields are compiled, otherwise false.
*/
function validateAllFieldsRequired(formId){
	var form = document.getElementById(formId);
	var isRequiredAll = false;

	for(var ctrl in form.elements){
		if(form.elements[ctrl].type == 'text' ||
                form.elements[ctrl].type == 'textarea'){

        	isRequiredAll = (form.elements[ctrl].value == '' || form.elements[ctrl].value == null);
        }
        if(isRequiredAll)break;
	}

 	return !isRequiredAll;
}

/**
* Submit a given form (formId) with the given action (action) checking if all fields are compiled.
* If not it doesn't submit form  and show an error message (message)
*/
function sendFormFieldsrequired(action, formId, message){

	if(validateAllFieldsRequired(formId)){
		sendForm(action, formId);
	}else{
		alert(message);
	}
}

/**
* Set focus of the first imput field of a given form (formId)
*/
function setFormFocus(formId){
	 var form = document.getElementById(formId);
 	 var len = form.elements.length;

 	 for(var i = 0;i < len;i++){
 	 	var curElement = form.elements[i];

 	 	if(curElement.constructor  == HTMLInputElement && !curElement.disabled){
 	 		curElement.focus();
 	 		return;
 	 	}
 	 }
}

/**
* Extract from string (dateTarget) day, month an year given a pattern (pattern) date (example: dd/mm/yyyy).
*/

function parseDate(pattern, dateTarget){
	var arr = new Array();
	
	//split date in its digits
	var  splittedDate = dateTarget.split(/[^0-9]/);
	
	dateTarget = ""
	
	//build dateTarget with only digits. Es: 5/5/1978 --> 05051978	
	for (var i = 0; i < splittedDate.length; i++) {
		var dateToken = splittedDate[i];
		if(dateToken.length < 2 )dateToken = "0" + dateToken
		
		dateTarget += dateToken
	}
	
	//clear all charaters different from mdy or MDY
	var flatPattern = pattern.replace(/[^mdyMDY]/g, "");
	
	arr["yyyy"] = dateTarget.substr(flatPattern.indexOf("yyyy"),4);
	arr["mm"] = dateTarget.substr(flatPattern.indexOf("mm"),2);
	arr["dd"] = dateTarget.substr(flatPattern.indexOf("dd"),2);

	return arr;
	
}

/**
* Convert a string (dateTarget) into a date given a pattern (pattern) date (example: dd/mm/yyyy).
*/
function stringToDate(pattern, dateTarget){
	var arr = parseDate(pattern, dateTarget);
	
	var d = new Date();

	d.setFullYear(arr["yyyy"], arr["mm"] - 1, arr["dd"]);
	d.setHours(0,0,0,0);

	return d;
}

/**
* Just an utility method. Convert 2 digits year in 4 digits. Es: 11/11/54 --> 11/11/1954
*/
function normalizeDate(pattern, dateTarget){
	//if date s empty exit
	if(dateTarget == "") return dateTarget;
	
		
	var arr = parseDate(pattern, dateTarget);
	
	if(arr["dd"] == "" || arr["mm"] == "" || arr["yyyy"] == "")
	return originalDateTarget;
	
	//if year is only 2 digits xx, if xx <= 20 becames 20xx, otherwise 19xx
	if(arr["yyyy"].length == 2 && arr["yyyy"] > 20 )
		arr["yyyy"] = "19" + arr["yyyy"];
	else if(arr["yyyy"].length == 2 && arr["yyyy"] <= 20 )
		arr["yyyy"] = "20" + arr["yyyy"];

	pattern = pattern.replace("dd", arr["dd"]);
	pattern = pattern.replace("mm", arr["mm"]);
	pattern = pattern.replace("yyyy", arr["yyyy"]);
	
	return pattern;
}
/**
* Set focus on a given field and set inner text's color to red.
*/
function setFieldNotValid(fieldId){
	var field = document.getElementById(fieldId);
	field.style.color = "red";
	//field.style.font-weight = "bold";

	field.setAttribute("onkeydown", "style.color = 'black'");

	field.focus();
}

/**
* Determinate if an parson is adult (es: more then 18 years old).
*/
function isAdult(dateTarget, majorityAge){
	var today = new Date();

	if(today.getYear() - dateTarget.getYear() <  18){
		//alert(typeof(majorityAge));
		return false;	
	}
	else if(today.getYear() - dateTarget.getYear() >  18)
		return true;
	else if(today.getMonth() < dateTarget.getMonth()){
		return false;
	}else if(today.getMonth() == dateTarget.getMonth()){
	if(today.getDate() < dateTarget.getDate())
	 return false;
	}
	
	return true;
}


/**
* Set as selected the option with a given value (selectedValue)
*/
function setOptionValue(selectId, optionValue){
	var selectOptions = document.getElementById(selectId);
	
	selectOptions.value = optionValue;			
}