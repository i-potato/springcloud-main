package org.yyf.springcloud.user.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyf.springcloud.commons.config.BusinessException;
import org.yyf.springcloud.user.service.dao.UserMapper;
import org.yyf.springcloud.user.service.entity.User;
import org.yyf.springcloud.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserMapper userMapper;
	

	@Override
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	public User getById(String id) {
		User user = userMapper.get(id);
		if(user == null ) {
			throw new BusinessException(1, "用户不存在");
		}
		log.info(user.toString());
		return user;
	}

	@Override
	public List<User> list() {
		return userMapper.list();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
	}

}
