package org.yyf.springcloud.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyf.springcloud.news.entity.News;

@Mapper
public interface NewsMapper {
	
	@Select("select * from news where id = #{id} ")
	News get(String id);
	
	@Select("select * from news")
	List<News> list();
	
}
