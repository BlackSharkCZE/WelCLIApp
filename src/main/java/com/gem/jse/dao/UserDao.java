package com.gem.jse.dao;

import com.gem.jse.dao.commons.jpa.DaoTemplate;
import com.gem.jse.entities.UserEntity;

import java.util.List;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 18.9.15<br/>
 * Time: 11:47<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
public class UserDao extends DaoTemplate<UserEntity> implements UserDaoInterface {
	public UserDao() {
		super(UserEntity.class);
	}

	@Override
	public List<UserEntity> findNativeAll() {
		return findByNamedQuery("UserEntity.native.findAll", 0, -1);
	}

	@Override
	public List<UserEntity> findAllHQL() {
		return findByNamedQuery("UserEntity.jpa.findAll", 0, -1);
	}
}
