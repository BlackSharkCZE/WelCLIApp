package com.gem.jse.weld;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 9:36<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public class WeldContext {

	private static final WeldContext instance = new WeldContext();

	private final Weld weld;
	private final WeldContainer container;

	private WeldContext() {
		this.weld = new Weld();
		this.container = weld.initialize();
	}

	public <T> T getBean(Class<T> type) {
		return container.instance().select(type).get();
	}

	public static WeldContext getInstance() {
		return instance;
	}

}
