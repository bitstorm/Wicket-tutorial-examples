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
import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.nested.BranchItem;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.PackageResourceReference;


public class AutocheckedFolder<T> extends CheckedFolder<T> {

	private ITreeProvider<T> treeProvider;
	private IModel<Set<T>> checkedNodes;
	private IModel<Boolean> checkboxModel;
	
	public AutocheckedFolder(String id, AbstractTree<T> tree, 
							 IModel<T> model, IModel<Set<T>> checkedNodes) {
		super(id, tree, model);	
		this.treeProvider = tree.getProvider();
		this.checkedNodes = checkedNodes;
		
		setOutputMarkupId(true);
	}
	
	@Override
	protected IModel<Boolean> newCheckBoxModel(IModel<T> model) {
		checkboxModel =  new CheckModel();
		return checkboxModel;
	}
	
	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		super.onUpdate(target);
		T node = getModelObject();
		boolean nodeChecked = checkboxModel.getObject();
		
		addRemoveSubNodes(node, nodeChecked);				
		addRemoveAncestorNodes(node, nodeChecked);
		
		updateNodeOnClientSide(target, nodeChecked);		
	}

	protected void updateNodeOnClientSide(AjaxRequestTarget target,
			boolean nodeChecked) {
		target.appendJavaScript(";CheckAncestorsAndChildren.checkChildren('" + getMarkupId() + "'," 
				+ nodeChecked + ");");
		
		target.appendJavaScript(";CheckAncestorsAndChildren.checkAncestors('" + getMarkupId() + "'," 
				+ nodeChecked + ");");
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		PackageResourceReference scriptFile = new PackageResourceReference(this.getClass(), "autocheckedFolder.js");
		response.render(JavaScriptHeaderItem.forReference(scriptFile));
	}
	
	protected void addRemoveSubNodes(T node, Boolean nodeStatus) {
		Iterator<? extends T> childrenNodes = treeProvider.getChildren(node);
		
		while (childrenNodes.hasNext()) {
			T childNode = childrenNodes.next();
			addRemoveSubNodes(childNode, nodeStatus);
		}
		
		if(nodeStatus)
			checkedNodes.getObject().add(node);
		else
			checkedNodes.getObject().remove(node);
	}
	
	protected void addRemoveAncestorNodes(T node, Boolean nodeStatus) {
		BranchItem currentConatiner = findParent(BranchItem.class);
		
		if(currentConatiner == null) return;
		
		BranchItem ancestor = currentConatiner.findParent(BranchItem.class);
		
		while(ancestor != null)
		{
			T nodeObject = (T) ancestor.getDefaultModelObject();
			
			if(nodeStatus)
			{
				checkedNodes.getObject().add(nodeObject);
			}
			else
			{
				if(!hasNodeCheckedChildren(nodeObject))
					checkedNodes.getObject().remove(nodeObject);				
			}
			
			ancestor = ancestor.findParent(BranchItem.class);
		}
	}

	protected boolean hasNodeCheckedChildren(T nodeObject) {
		Iterator<? extends T> children = treeProvider.getChildren(nodeObject);
		
		while (children.hasNext()) {
			T child = children.next();
			if(checkedNodes.getObject().contains(child))
				return true;
		}
		
		return false;
	}

	class CheckModel extends AbstractCheckBoxModel{
		@Override
		public boolean isSelected() {
			return checkedNodes.getObject()
					.contains(getModelObject());
		}

		@Override
		public void select() {
			checkedNodes.getObject().add(getModelObject());
		}

		@Override
		public void unselect() {
			checkedNodes.getObject().remove(getModelObject());
		}				
	}
}
