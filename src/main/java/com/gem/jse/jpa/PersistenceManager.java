package com.gem.jse.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 10:12<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public class PersistenceManager {

	private static final PersistenceManager manager = new PersistenceManager();
	private final EntityManagerFactory managerFactory;


	private PersistenceManager() {
		managerFactory = Persistence.createEntityManagerFactory("JPA_PERSISTENCE_UNIT");
	}

	public static PersistenceManager getInstance() {
		return manager;
	}

	public EntityManager getEntityManager() {
		return managerFactory.createEntityManager();
	}

	public void close() {
		managerFactory.close();
	}

}
