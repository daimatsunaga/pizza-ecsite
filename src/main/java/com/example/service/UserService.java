package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
