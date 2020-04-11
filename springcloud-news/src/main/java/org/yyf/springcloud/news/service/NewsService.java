package org.yyf.springcloud.news.service;

import java.util.List;

import org.yyf.springcloud.news.entity.News;

public interface NewsService {
	
	News get(String id);
	
	List<News> list();
	
	
	
}
