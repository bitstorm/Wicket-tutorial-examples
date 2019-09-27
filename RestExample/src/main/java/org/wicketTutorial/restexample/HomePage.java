package org.wicketTutorial.restexample;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;

import de.agilecoders.wicket.core.Bootstrap;

public class HomePage extends BootstrapBasePage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

	}
	
	
	@Override
	public void renderHead(IHeaderResponse response) {
	    super.renderHead(response);
	    
	    JavaScriptHeaderItem uiCode = JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(getClass(), "ui-code.js") {
	        @Override
	        public List<HeaderItem> getDependencies() {
	            ResourceReference bootstrapRef = Bootstrap.getSettings().getJsResourceReference();

                return Arrays.asList(JavaScriptHeaderItem.forReference(bootstrapRef));
	        }
	    });
	    
	    response.render(uiCode);
	}
}
