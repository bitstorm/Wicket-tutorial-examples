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
package org.wicketTutorial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;

import org.apache.wicket.model.LoadableDetachableModel;

public class JpaLoadableModel<T> extends LoadableDetachableModel<T> {
	
	private EntityManagerFactory entityManagerFactory;
	private Class<T> entityClass;
	private Serializable identifier;
	private List<Object> constructorParams;
	
	public JpaLoadableModel(EntityManagerFactory entityManagerFactory, T entity) {
		super();
		PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
		
		this.entityManagerFactory = entityManagerFactory;
		this.entityClass = (Class<T>) entity.getClass();
		this.identifier = (Serializable) util.getIdentifier(entity);
		
		setObject(entity);
	}
	
	@Override
	protected T load() {
		T entity = null;
		
		if(identifier != null){	
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entity = entityManager.find(entityClass, identifier);
		}
		
		return entity;
	}
		
	@Override
	protected void onDetach() {
		super.onDetach();
		
		T entity = getObject();
		PersistenceUnitUtil persistenceUtil = entityManagerFactory.getPersistenceUnitUtil();
		
		if(entity == null) return;
		
		identifier = (Serializable) persistenceUtil.getIdentifier(entity);		
	}
}
