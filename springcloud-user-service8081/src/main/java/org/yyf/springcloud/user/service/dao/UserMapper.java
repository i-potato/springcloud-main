package org.yyf.springcloud.user.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyf.springcloud.user.service.entity.User;

@Mapper
public interface UserMapper {
	@Select("select * from user where id = #{id}")
	User get(String id);
	
	@Insert("insert into user values(#{id},#{userName},#{userPassword})")
	void insert(User user);
	
	@Delete("delete from user where id = #{id}")
	void delete(String id);
	
	@Select("select * from user")
	List<User> list();
	
	@Select("select * from user where user_name = #{userName} and user_password = #{userPassword}")
	User login(User user);
}
