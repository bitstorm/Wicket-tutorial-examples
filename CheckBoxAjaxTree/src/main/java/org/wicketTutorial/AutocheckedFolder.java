package org.wicketTutorial;
import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;


public class AutocheckedFolder<T> extends CheckedFolder<T> {

	private ITreeProvider<T> treeProvider;
	private boolean nodeChecked;
	private IModel<Set<T>> treeModel;
	
	public AutocheckedFolder(String id, AbstractTree<T> tree, IModel<T> model) {
		super(id, tree, model);	
		this.treeProvider = tree.getProvider();
		this.treeModel = tree.getModel();
	}
	
	@Override
	protected IModel<Boolean> newCheckBoxModel(IModel<T> model) {
		return new PropertyModel<Boolean>(this, "nodeChecked");
	}
	
	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		super.onUpdate(target);
		
		if(nodeChecked)
		{
			T node = getModelObject();
			addRemoveSubNodes(node);
		}
	}
	
	private void addRemoveSubNodes(T node) {
		Iterator<? extends T> childrenNodes = treeProvider.getChildren(node);
		
		while (childrenNodes.hasNext()) {
			T childNode = childrenNodes.next();
			addRemoveSubNodes(childNode);
		}
		
		treeModel.getObject().add(node);
	}
}
