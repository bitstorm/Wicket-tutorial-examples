$( function() {	
	$( "#exampleSearch" ).autocomplete({
      source: $('a.exampleLink').toArray().map(function( item ) {
    	  var pair = {
			    value: item.href,
			    label: item.innerText
		  };
    	  
    	  return pair;
      })
    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
  	  return $( "<li>" )
	  .attr( "data-value", item.value )
	  .append( $( "<a>" ).attr( "href", item.value ).append(item.label) )
	  .appendTo( ul );
   };
  } );