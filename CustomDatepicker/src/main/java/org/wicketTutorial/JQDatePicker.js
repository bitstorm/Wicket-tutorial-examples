function initCalendar(localizedArray){
	 localizedArray['changeMonth']= true;
	 localizedArray['changeYear']= true;
	 localizedArray['showOn'] = 'button';
	 localizedArray['buttonImageOnly'] = true;
};
	
function initJQDatapicker(inputId, countryIsoCode, calendarIcon) {
	var localizedArray = $.datepicker.regional[countryIsoCode];
	localizedArray['buttonImage'] = calendarIcon;
	initCalendar(localizedArray);
	$("#" + inputId).datepicker(localizedArray);
};
