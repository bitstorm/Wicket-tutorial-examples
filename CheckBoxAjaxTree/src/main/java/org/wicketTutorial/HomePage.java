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

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.extensions.markup.html.repeater.util.TreeModelProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private NestedTree<DefaultMutableTreeNode> tree;	

	public HomePage(final PageParameters parameters) {
		super(parameters);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cities of Europe");
		
		addNodes(addNodes(root, "Italy"), "Rome", "Venice", "Milan", "Florence");
		addNodes(addNodes(root, "Germany"), "Stuttgart", "Munich", "Berlin", "Dusseldorf", "Dresden");
		addNodes(addNodes(root, "France"), "Paris ", "Toulouse", "Strasbourg", "Bordeaux", "Lyon");
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		TreeModelProvider<DefaultMutableTreeNode> modelProvider = new TreeModelProvider<DefaultMutableTreeNode>(treeModel) {
			
			@Override
			public IModel<DefaultMutableTreeNode> model(DefaultMutableTreeNode object) {
				return Model.of(object);
			}
		};
		
		tree = new NestedTree<DefaultMutableTreeNode>("tree", modelProvider)
	    {
	        private static final long serialVersionUID = 1L;
	        
	        @Override
	        protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model)
	        {
	            return new CheckedFolder<DefaultMutableTreeNode>(id, this, model);
	        }
	    };
	    //select Windows theme
	    tree.add(new WindowsTheme());
	    
	    add(tree);
    }	

    protected NestedTree<DefaultMutableTreeNode> getTree() {
		return tree;
	}
    
    /**
     * Creates a child node for every string in input 
     * @param parent
     * @param childrenNode
     * @return the last child node added to parent node
     */
    public static DefaultMutableTreeNode addNodes(DefaultMutableTreeNode parent, String... childrenNode){
    	DefaultMutableTreeNode newNode = null;
    	
    	for (int i = 0; i < childrenNode.length; i++) {
    		newNode = new DefaultMutableTreeNode(childrenNode[i]);    	
        	parent.add(newNode);    	
		}
    	return newNode;
    }       
}
