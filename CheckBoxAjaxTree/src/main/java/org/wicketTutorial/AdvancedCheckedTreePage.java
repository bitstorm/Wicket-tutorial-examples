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
package org.wicketTutorial;

import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.SetModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AdvancedCheckedTreePage extends HomePage {

	public AdvancedCheckedTreePage(PageParameters parameters) {
		super(parameters);		
		
		NestedTree<DefaultMutableTreeNode> tree = getTree();
		RepeatingView selectedNodes = new RepeatingView("nodes");
		final WebMarkupContainer markupContainer = new WebMarkupContainer("checkedNodes");
		final Set<DefaultMutableTreeNode> checkedNodes = new HashSet<DefaultMutableTreeNode>();
		
		tree = new NestedTree<DefaultMutableTreeNode>("tree", tree.getProvider())
	    {
	        SetModel<DefaultMutableTreeNode> checkedNodesModel = new SetModel<DefaultMutableTreeNode>(
	        		checkedNodes);
	        
	        @Override
	        protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model)
	        {
	            return new AutocheckedFolder<DefaultMutableTreeNode>(id, this, model, checkedNodesModel);
	        }
	    };
	    
	    tree.add(new AjaxEventBehavior("click") {
			
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				RepeatingView selectedNodes = new RepeatingView("nodes");
				
				for(DefaultMutableTreeNode node :checkedNodes){
					selectedNodes.add(new Label(selectedNodes.newChildId(), node.toString()));
				}
				
				if(checkedNodes.size() == 0)
					markupContainer.setVisible(false);
				else
					markupContainer.setVisible(true);
				
				markupContainer.replace(selectedNodes);
				target.add(markupContainer);
			}
		});
	    //select Windows theme
	    tree.add(new WindowsTheme());
	    
	    markupContainer.add(selectedNodes);
	    markupContainer.setVisible(false);
	    add(markupContainer.setOutputMarkupPlaceholderTag(true));	    
	    addOrReplace(tree);
	}
	
}
