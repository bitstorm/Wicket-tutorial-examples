/**
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
package org.wicketTutorial.util;

import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.response.filter.IResponseFilter;
import org.apache.wicket.util.string.AppendingStringBuffer;

public class BackHomeFilter extends WicketFilter
{
	@Override
	public void init(boolean isServlet, FilterConfig filterConfig) throws ServletException
	{
		super.init(isServlet, filterConfig);
		
		WebApplication application = getApplication();
		
		application.getRequestCycleSettings().addResponseFilter(new IResponseFilter()
		{
			
			@Override
			public AppendingStringBuffer filter(AppendingStringBuffer responseBuffer)
			{
				WebRequest request = (WebRequest)RequestCycle.get().getRequest();
				
				if (!request.isAjax())
				{		
					List<String> segments = request.getUrl().getSegments();
					String sourceClass = "";
					
					//read the target class from bookmarkable url or use the Application class
					if (segments.size() > 0)
					{						
						sourceClass = segments.get(segments.size() - 1);
					}
					else 
					{
						sourceClass = getApplication().getClass().getName();
					}
					
					responseBuffer.append("<br/><br/><a class='hide-homelink' href='#' onclick=\"window.open('/seecode?SourcesPage_class=" + sourceClass +
										  "', 'sources', 'scrollbars=no,location=no,menuBar=no,resizable=yes,status=no,toolbar=no,width=900,height=600')\">See Source</a>");
					responseBuffer.append("<br/><a class='hide-homelink view-sourcelink' href='http://examples-wickettutorial.rhcloud.com'>Go back to the Examples</a>");
				}
				return responseBuffer;
			}
		});
	}
}
