package org.wicketTutorial;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.extensions.markup.html.repeater.util.TreeModelProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cities of Europe");
		
		addNodes(addNode(root, "Italy"), "Venice", "Milan", "Florence");
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		TreeModelProvider<String> modelProvider = new TreeModelProvider<String>(treeModel) {
			
			@Override
			public IModel<String> model(String object) {
				return Model.of(object);
			}
		};
		
		NestedTree<String> tree = new NestedTree<String>("tree", modelProvider)
	    {
	        private static final long serialVersionUID = 1L;
	        
	        @Override
	        protected Component newContentComponent(String id, IModel<String> model)
	        {
	            return new CheckedFolder<String>(id, this, model);
	        }
	    };
	    
	    add(tree);
    }
    
    public static void addNodes(DefaultMutableTreeNode parent, String... childrenNode){
    	for (int i = 0; i < childrenNode.length; i++) {
			addNode(parent, childrenNode[i]);
		}
    }
    
    public static DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, String childNode){
    	DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(childNode);    	
    	parent.add(newNode);
    	
    	return newNode;
    }
}
