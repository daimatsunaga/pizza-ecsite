package com.example.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {
	
	private static final RowMapper<User> USER_ROW_MAPPER =(rs,i)->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	/**
	 * パスワードとemailでuserを検索
	 * @param password
	 * @param email
	 * @return user情報1件
	 */
	public User  findByPasswordAndMailAddress(String email,String password) {
		String sql ="SELECT id,name,email,password,zipcode,address,telephone "
				+ "FROM users WHERE password=:password AND email=:email";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email).addValue("password",password);
		User user= template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;		
	}

	/**
	 * ユーザー登録を行う
	 * @param user
	 */
	public void insert(User user) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users (name, email, password, zipcode, address, telephone) "
				+ "VALUES (:name, :email, :password, :zipcode, :address, :telephone);";	
		template.update(sql, param);		
	}
	
}
