package org.yyf.springcloud.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springcloud.commons.config.ResponseMap;
import org.yyf.springcloud.user.service.entity.User;
import org.yyf.springcloud.user.service.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "{id}" , method = RequestMethod.GET)
	public ResponseMap<User> get(@PathVariable String id){
		User user = userService.getById(id);
		return new ResponseMap<User>(user);
	}
	
	
}
