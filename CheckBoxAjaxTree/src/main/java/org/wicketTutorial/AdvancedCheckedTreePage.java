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
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cities of Europe");
		
		addNodes(addNode(root, "Italy"), "Rome", "Venice", "Milan", "Florence");
		addNodes(addNode(root, "Germany"), "Stuttgart", "Munich", "Berlin", "Dusseldorf", "Dresden");
		addNodes(addNode(root, "France"), "Paris ", "Toulouse", "Strasbourg", "Bordeaux", "Lyon");
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		TreeModelProvider<DefaultMutableTreeNode> modelProvider = new TreeModelProvider<DefaultMutableTreeNode>(treeModel) {
			
			@Override
			public IModel<DefaultMutableTreeNode> model(DefaultMutableTreeNode object) {
				return Model.of(object);
			}
		};
		
		NestedTree<DefaultMutableTreeNode> tree = new NestedTree<DefaultMutableTreeNode>("tree", modelProvider)
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
