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

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.extensions.markup.html.repeater.util.TreeModelProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.SetModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AdvancedCheckedTreePage extends HomePage {

	public AdvancedCheckedTreePage(PageParameters parameters) {
		super(parameters);		
		NestedTree<DefaultMutableTreeNode> tree = getTree();
		
		tree = new NestedTree<DefaultMutableTreeNode>("tree", tree.getProvider())
	    {
	        SetModel<DefaultMutableTreeNode> checkedNodes = new SetModel<DefaultMutableTreeNode>(
	        		new HashSet<DefaultMutableTreeNode>());
	        
	        @Override
	        protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model)
	        {
	            return new AutocheckedFolder<DefaultMutableTreeNode>(id, this, model, checkedNodes);
	        }
	    };
	    //select Windows theme
	    tree.add(new WindowsTheme());
	    
	    addOrReplace(tree);
	}
	
}
