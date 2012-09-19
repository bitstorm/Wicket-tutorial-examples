$(function() {
	var localizedArray = $.datepicker.regional["${countryIsoCode}"];
		localizedArray['buttonImage'] = "${calendarIcon}";
		initCalendar(localizedArray);
		$("#${inputId}").datepicker(localizedArray);
	});
