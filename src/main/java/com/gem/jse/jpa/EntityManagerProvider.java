package com.gem.jse.jpa;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 13:53<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */

public class EntityManagerProvider {

	@Produces
	public EntityManager getEntityManager() {
		return PersistenceManager.getInstance().getEntityManager();
	}

}
