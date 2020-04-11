package org.yyf.springcloud.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyf.springcloud.commons.config.BusinessException;
import org.yyf.springcloud.news.dao.NewsMapper;
import org.yyf.springcloud.news.entity.News;
import org.yyf.springcloud.news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NewsMapper newsMapper;

	@Override
	public News get(String id) {
		News news = new News();
		try {
			news = newsMapper.get(id);
		} catch (Exception e) {
			log.error("数据库访问失败",e);
			throw new BusinessException(1, "数据库访问失败");
		}

		
		return news;
	}

	@Override
	public List<News> list() {
		List<News> news = new ArrayList<>();
		try {
			news = newsMapper.list();
		} catch (Exception e) {
			log.error("数据库访问失败",e);
			throw new BusinessException(1, "数据库访问失败");
		}
		return news;
	}
	
}
