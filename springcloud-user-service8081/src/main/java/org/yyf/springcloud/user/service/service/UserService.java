package org.yyf.springcloud.user.service.service;

import java.util.List;

import org.yyf.springcloud.user.service.entity.User;

public interface UserService {
	
	void insert(User user);
	
	User getById(String id,String auth);
	
	List<User> list();
	
	void update(User user);
	
	String login(User user);
	
}
