package com.gem.jse.dao;

import com.gem.jse.dao.commons.jpa.DaoInterface;
import com.gem.jse.entities.UserEntity;

import java.util.List;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 11:41<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public interface UserDaoInterface extends DaoInterface<UserEntity> {

	List<UserEntity> findNativeAll();
	List<UserEntity> findAllHQL();

}
