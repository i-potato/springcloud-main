package org.yyf.springcloud.news.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yyf.springcloud.commons.config.ResponseMap;

@Service
@FeignClient(value = "springcloud-user-service")
public interface UserAccessService {
	
//	@GetMapping("user/auth/{auth}")
	@RequestMapping(value = "user/auth/{auth}", method = RequestMethod.GET)
	ResponseMap<Void> auth(@PathVariable("auth") String auth);
	
}
