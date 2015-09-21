package com.gem.jse;

import com.gem.jse.dao.UserDaoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 9:38<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Inject
	private UserDaoInterface userDaoInterface;

	public void run(String[] args) {
		logger.debug("Application Start");
		System.out.println("Hello World!");
	}
}
