package org.yyf.springcloud.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springcloud.commons.config.BusinessException;
import org.yyf.springcloud.commons.config.ResponseMap;
import org.yyf.springcloud.news.entity.News;
import org.yyf.springcloud.news.service.NewsService;
import org.yyf.springcloud.news.service.UserAccessService;

/**
 * !!!访问news下的接口需要Authorization
 * @author yyf
 * @date   2020年4月12日
 */
@RestController
@RequestMapping("news")
public class NewsController {
	
	@Autowired
	NewsService newsService;
	
	@Autowired
	UserAccessService userAccessService;
	
	
	/**
	 * 查询单个新闻
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseMap<News> get(@PathVariable String id,HttpServletRequest request ){
		
		//用户验证  这里只做演示   实际应该使用拦截器 
		String auth = request.getHeader("Authorization");
		//这里需要判断一下，否则auth为空访问到user-service返回404
		if(StringUtils.isEmpty(auth)) {
			return new ResponseMap<>(1,"用户验证失败");
		}
		
		ResponseMap<Void> resMap = userAccessService.auth(auth);
		
		if(resMap.getResult() != 0) {
			return new ResponseMap<>(1,"用户验证失败");
		}
		
		News news = new News();
		try {
			news = newsService.get(id);
		} catch (BusinessException be) {
			return new ResponseMap<>(be.getErrorCode(),be.getMessage());
		} catch (Exception e) {
			return new ResponseMap<>(1,"系统错误");
		}
		
		
		return new ResponseMap<>(news);
	}
	
	
	
	/**
	 * 查询新闻列表
	 * @param request
	 * @return
	 */
	@GetMapping("list")
	public ResponseMap<List<News>> list(HttpServletRequest request){
		
		//用户验证  这里只做演示   实际应该使用拦截器 
		String auth = request.getHeader("Authorization");
		//这里需要判断一下，否则auth为空访问到user-service返回404
		if(StringUtils.isEmpty(auth)) {
			return new ResponseMap<>(1,"用户验证失败");
		}
		
		ResponseMap<Void> resMap = userAccessService.auth(auth);
		
		if(resMap.getResult() != 0) {
			return new ResponseMap<>(1,"用户验证失败");
		}
		
		List<News> news = new ArrayList<News>();
		
		try {
			news = newsService.list();
		} catch (BusinessException be) {
			return new ResponseMap<>(be.getErrorCode(),be.getMessage());
		} catch (Exception e) {
			return new ResponseMap<>(1,"系统错误");
		}
		
		return new ResponseMap<>(news);
	}
	
	
}
