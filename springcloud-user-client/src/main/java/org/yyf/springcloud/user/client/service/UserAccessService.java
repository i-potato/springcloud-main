package org.yyf.springcloud.user.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yyf.springcloud.commons.config.ResponseMap;
import org.yyf.springcloud.user.client.entity.User;

@Service
@FeignClient(value = "springcloud-user-service")
public interface UserAccessService {
	
	/**
	 * openFeignClient演示
	 * 配置访问服务名称为springcloud-user-service的user/{id}接口
	 * @PathVariable("id")中的"id"不能省略
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "user/get/{id}", method = RequestMethod.GET)
	ResponseMap<User> get(@PathVariable("id") String id);
	
	
	/**
	 * 负载均衡测试
	 * 请访问http://localhost:8080/user/lb
	 * @return
	 */
//	@PostMapping
	@GetMapping("user/lb")
	String lb();
	
}
