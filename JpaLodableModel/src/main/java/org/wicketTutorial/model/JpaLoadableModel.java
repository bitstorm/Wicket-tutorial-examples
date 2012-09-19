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
