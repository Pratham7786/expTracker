package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.entity.userEntity;

@Repository
public class userDao {
	
	@Autowired
	JdbcTemplate stmt;
	
	public void addUsers(userEntity users)
	{
		stmt.update("insert into users (firstname,lastname,email,password,gender,role,createdat,profilepicpath) values(?,?,?,?,?,?,?,?)",users.getFirstname(),users.getLastname(),users.getEmail(),users.getPassword(),users.getGender(),users.getRole(),users.getCreatedat(),users.getProfilepicpath());
	}
	public userEntity getbyEmail(String email) {
		userEntity mail = null;
		try {
			mail = stmt.queryForObject("select * from users where email = ? ",new BeanPropertyRowMapper<>(userEntity.class), new Object[] {email});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}
}
