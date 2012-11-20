package org.wicketTutorial;
import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;


public class AutocheckedFolder<T> extends CheckedFolder<T> {

	private ITreeProvider<T> treeProvider;
	private boolean selected;
	
	public AutocheckedFolder(String id, AbstractTree<T> tree, IModel<T> model) {
		super(id, tree, model);	
		this.treeProvider = tree.getProvider();
	}
	
	@Override
	protected IModel<Boolean> newCheckBoxModel(IModel<T> model) {
		return new PropertyModel<Boolean>(this, "selected");
	}
	
	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		super.onUpdate(target);
		System.out.println("let's see!");
		
		if(isSelected())
		{
			T node = getModelObject();
			Iterator<? extends T> childrenNodes = treeProvider.getChildren(node);
			System.out.println("SELECTED!");
		}
	}
}
