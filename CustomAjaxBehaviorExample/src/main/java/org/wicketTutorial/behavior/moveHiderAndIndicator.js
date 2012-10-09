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
DisableComponentListener = {
	disableElement: function(elementId, activeIconUrl){
      var hiderId = elementId + "-disable-layer";
      var indicatorId = elementId + "-indicator-picture";
      
      elementId = "#" + elementId;
      //create the overlay <div>
      $(elementId).after('<div id="' + hiderId 
         + '" style="position:absolute;">'
         + '<img id="' + indicatorId +  '" src="' + activeIconUrl + '"/>'
         + '</div>');
      
      hiderId = "#" + hiderId;
      //setting the style properties of the overlay <div>
      $(hiderId).css('opacity', '0.8');               
      $(hiderId).css('text-align', 'center');
      $(hiderId).css('background-color', 'WhiteSmoke');
      $(hiderId).css('border', '1px solid DarkGray');
      //setting the dimention of the overlay <div>
      $(hiderId).width($(elementId).outerWidth());
      $(hiderId).height($(elementId).outerHeight());       	 
      //positioning the overlay <div> on the component to disable.     
      $(hiderId).position({of: $(elementId),at: 'top left', my: 'top left'});
       
      //positioning the activity indicator in the middle of overlay <div>
      $("#" + indicatorId).position({of: $(hiderId), at: 'center center',
                                     my: 'center center'});
   },

	hideComponent: function(elementId){
		var hiderId = elementId + "-disable-layer";
		$('#' + hiderId).remove();
	}
};
