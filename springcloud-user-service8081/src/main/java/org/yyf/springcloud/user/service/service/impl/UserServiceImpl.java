package org.yyf.springcloud.user.service.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.Objenesis;
import org.springframework.stereotype.Service;
import org.yyf.springcloud.commons.config.BusinessException;
import org.yyf.springcloud.commons.config.ResponseMap;
import org.yyf.springcloud.commons.jwt.JwtObjectUtil;
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
	public User getById(String id,String auth) {
		if(StringUtils.isBlank(auth)) {
			throw new BusinessException(1, "未登录");
		}
		
		User userLogin = JwtObjectUtil.unSign(auth, User.class);
		
		if(userLogin == null || userMapper.login(userLogin) == null ) {
			throw new BusinessException(1, "未授权的用户");
		}
		
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

	@Override
	public String login(User user) {
		// TODO Auto-generated method stub
		User userLogin =  userMapper.login(user);
		
		if(userLogin == null) {
			throw new BusinessException(1, "登录失败，用户名或密码错误");
		}
		
		String auth = JwtObjectUtil.sign(userLogin);
		
		return auth;
	}

	
	
}
