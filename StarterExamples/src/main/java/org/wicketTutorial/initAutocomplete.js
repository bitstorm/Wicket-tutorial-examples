$( function() {	
	$( "#exampleSearch" ).autocomplete({
      source: $('a.exampleLink').toArray().map(function( item ) {
    	  var pair = {
			    value: item.href,
			    label: item.innerText
		  };
    	  
    	  return pair;
      }),
      select: function (event, ui) {
    	  $( "#exampleSearch" ).val(ui.item.label);
    	  location.href = ui.item.value;
    	  return false;
      },
      focus: function (event, ui) {
    	  return false;
      }
    });
  } );