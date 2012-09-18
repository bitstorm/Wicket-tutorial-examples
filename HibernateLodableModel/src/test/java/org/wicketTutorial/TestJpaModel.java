package org.wicketTutorial;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.junit.Assert;
import org.junit.Test;
import org.wicketTutorial.model.JpaLoadableModel;


public class TestJpaModel {
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;

	public TestJpaModel(){
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );	
		entityManager = entityManagerFactory.createEntityManager();		
	}
	
	@Test
	public void testPersistedEntity(){
		Person person = new Person("Bill", "Smith", "bsmith@gmail.com");
		
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		
		PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
		
		JpaLoadableModel<Person> model = new JpaLoadableModel(entityManagerFactory, person);
		model.detach();
		
		Assert.assertNotNull(model.getObject());	
		Assert.assertEquals(person.getId(), model.getObject().getId());		
	}
	
	@Test
	public void testTransientEntity() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Person person = new Person("Phill", "Griffin", "pgriffin@gmail.com");
		JpaLoadableModel<Person> model = new JpaLoadableModel(entityManagerFactory, person);
		
		Assert.assertNotNull(model.getObject());
		
		model.detach();
		
		Assert.assertNull(model.getObject());		
	}
	
	@Test
	public void testPersistTransientEntity(){
		Person person = new Person("Bill", "Smith", "bsmith@gmail.com");
		JpaLoadableModel<Person> model = new JpaLoadableModel(entityManagerFactory, person);
		
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		
		model.detach();
		
		Assert.assertEquals(person.getId(), model.getObject().getId());	
	}
	
	@Test
	public void testPersistedDeleteEntity(){
		Person person = new Person("Bill", "Smith", "bsmith@gmail.com");
		
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		
		JpaLoadableModel<Person> model = new JpaLoadableModel(entityManagerFactory, person);
		
		model.detach();
		Assert.assertNotNull(model.getObject());
		
		entityManager.getTransaction().begin();
		entityManager.remove(person);
		entityManager.getTransaction().commit();
		
		model.detach();
		Assert.assertNull(model.getObject());
		
	}
	
}
