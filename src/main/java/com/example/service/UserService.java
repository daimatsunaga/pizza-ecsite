package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	/**
	 * ユーザー登録を行う。
	 * @param user
	 */
	public void insert(User user) {
		repository.insert(user);
	}
	/**
	 * パスワードとemailでユーザー1件を取得 該当がない場合にはnullを返す。
	 * @param password
	 * @param email
	 * @return user1件
	 */
	public User findByPasswordAndMailAddress(String email, String password) {
		try {
			User user = repository.findByPasswordAndMailAddress(email, password);
			return user;			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
