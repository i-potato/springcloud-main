package org.yyf.springcloud.user.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springcloud.commons.config.BusinessException;
import org.yyf.springcloud.commons.config.ResponseMap;
import org.yyf.springcloud.commons.jwt.JwtObjectUtil;
import org.yyf.springcloud.user.service.entity.User;
import org.yyf.springcloud.user.service.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Value("${server.port}")
	private String port;
	
	/**
	 * 简单业务调用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "get/{id}" , method = RequestMethod.GET)
	public ResponseMap<User> get(@PathVariable String id,HttpServletRequest request){
		
		String auth = request.getHeader("Authorization");
		
		User user = new User();
		try {
			user = userService.getById(id,auth);
		} catch (BusinessException be) {
			log.info(be.getMessage());
			return new ResponseMap<>(be.getErrorCode(),be.getMessage());
		} catch (Exception e) {
			log.error("系统错误",e);
			return new ResponseMap<>(1,"系统错误");
		}
		return new ResponseMap<>(user);
	}
	
	/**
	 * 负载均衡测试
	 * @return
	 */
	@GetMapping("lb")
	public String lb() {
		StringBuffer sb = new StringBuffer();
		sb.append("当前访问端口：").append(port);
		return sb.toString();
	}
	
	
	
	@PostMapping("login")
	public ResponseMap<String> login(@RequestBody User user){
		String auth = "";
		try {
			auth = userService.login(user);
		} catch (BusinessException be) {
			log.info(be.getMessage());
			return new ResponseMap<>(be.getErrorCode(),be.getMessage());
		} catch (Exception e) {
			log.error("系统错误",e);
			return new ResponseMap<>(1,"系统错误");
		}
		return new ResponseMap<>(auth);
	}
	
	/**
	 * auth验证
	 * @return
	 */
//	@GetMapping("auth/{auth}")
	@RequestMapping(value = "auth/{auth}" , method = RequestMethod.GET)
	public ResponseMap<Void> auth(@PathVariable String auth){
		
		
		log.info("hi");
		
		if(StringUtils.isEmpty(auth)) {
			return new ResponseMap<>(1,"验证失败");
		}
		
		User user =  JwtObjectUtil.unSign(auth, User.class);
		
		try {
			if(StringUtils.isEmpty(userService.login(user))) {
				return new ResponseMap<>(1,"验证失败");
			}else {
				return new ResponseMap<>();
			}
		} catch (Exception e) {
			return new ResponseMap<>(1,"验证失败");
		}
		
		
	}
	
	
	
}
