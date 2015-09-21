package com.gem.jse.weld;

import com.gem.jse.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 9:34<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.debug("Main Application Started!");
		final Application app = WeldContext.getInstance().getBean(Application.class);
		app.run(args);
		logger.debug("Main Application Stop!");
	}

}
