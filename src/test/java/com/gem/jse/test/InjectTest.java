package com.gem.jse.test;


import com.gem.jse.support.EmptyBean;
import com.gem.jse.support.WeldJUnit4Runner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 9:44<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
@RunWith(WeldJUnit4Runner.class)
public class InjectTest {

	@Inject
	private EmptyBean emptyBean;


	@Test
	public void testInject() {
		Assert.assertNotNull(emptyBean);
		Assert.assertEquals(Integer.valueOf(1), emptyBean.getInt());
	}

}
