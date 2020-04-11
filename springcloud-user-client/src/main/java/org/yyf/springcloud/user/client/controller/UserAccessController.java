package org.yyf.springcloud.user.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springcloud.commons.config.ResponseMap;
import org.yyf.springcloud.commons.jwt.JwtObjectUtil;
import org.yyf.springcloud.user.client.entity.User;
import org.yyf.springcloud.user.client.service.UserAccessService;

import feign.RequestTemplate;

@RestController
@RequestMapping("user")
public class UserAccessController {
	
	@Autowired
	UserAccessService userAcessService;
	
	/**
	 * 访问userAcessService中的接口即可调用对应服务的接口
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}" , method = RequestMethod.GET)
	public ResponseMap<User> get(@PathVariable String id,HttpServletResponse response,HttpServletRequest request){
		
		//@TODO
		
		//调用user-service中的接口 需要Authorization
		return userAcessService.get(id);
	}
	
	/**
	 * 负载均衡测试 
	 * 请访问http://localhost:8080/user/lb或者通过网关http://localhost/user/lb
	 * @return
	 */
	@GetMapping("lb")
	public String lb() {
		return userAcessService.lb();
	}
	
}
