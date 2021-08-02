package com.Logiweb.LoginApp.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ApplicationObject {

	@PersistenceContext
	protected EntityManager em;

	// k K i I
	public <T> T save(Object obj) {
		em.persist(obj);
		return (T) obj;
	}

	public void delete (Object obj) {
		em.remove(obj);
		
	}

	public <T> T update(Object obj) {
		em.merge(obj);
		return (T) obj;
	}
	
	public <T>  T getById(Class type, Object Id) {
		
		return (T) em.find(type,Id );
	}
	
	
}
