/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.wicketTutorial;

import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Session;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

public class HomePage extends BootstrapBasePage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        setDefaultModel(new CompoundPropertyModel<HomePage>(this));

         add(new StatelessLink<Void>("linkToHttps") {
             /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
             public void onClick() {
                 setResponsePage(org.wicketTutorial.https.HomePage.class);
             }
         });

        add(new Label("wicket-version", getApplication().getFrameworkSettings().getVersion()));
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        Session.get().invalidateNow();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        HeaderItem item =
                JavaScriptHeaderItem.forUrl("https://buttons.github.io/buttons.js", "github");

        response.render(item);

        JavaScriptReferenceHeaderItem jqueryuiReference = JavaScriptHeaderItem.forReference(
                new WebjarsJavaScriptResourceReference("jquery-ui/current/jquery-ui.min.js") {
                    /**
                     * 
                     */
                    private static final long serialVersionUID = 1L;

                    @Override
                    public List<HeaderItem> getDependencies() {
                        ResourceReference jqueryRef =
                                getApplication().getJavaScriptLibrarySettings().getJQueryReference();

                        return Arrays.asList(JavaScriptHeaderItem.forReference(jqueryRef));
                    }
                }
        );
        
        response.render(jqueryuiReference);
        
        CssReferenceHeaderItem fontawesomeCss = CssReferenceHeaderItem.forReference(
                new WebjarsCssResourceReference("font-awesome/4.7.0/css/font-awesome.min.css"));
        response.render(fontawesomeCss);
        
        CssReferenceHeaderItem jqueryuiCss = CssReferenceHeaderItem.forReference(
                new WebjarsCssResourceReference("jquery-ui/current/jquery-ui.min.css"));

        response.render(jqueryuiCss);
        
        PackageResourceReference resourceReference = new PackageResourceReference(getClass(), "initAutocomplete.js") {
            @Override
            public List<HeaderItem> getDependencies() {
                return Arrays.asList(jqueryuiReference);
            }
        };
        JavaScriptHeaderItem initAutocomplete = JavaScriptHeaderItem.forReference(resourceReference);
        
        response.render(initAutocomplete);
    }
}
