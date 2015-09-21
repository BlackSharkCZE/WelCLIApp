package com.gem.jse.support;

import com.gem.jse.weld.WeldContext;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 9:42<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public class WeldJUnit4Runner extends BlockJUnit4ClassRunner {


	public WeldJUnit4Runner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Object createTest() {
		final Class<?> test = getTestClass().getJavaClass();
		return WeldContext.getInstance().getBean(test);
	}
}
