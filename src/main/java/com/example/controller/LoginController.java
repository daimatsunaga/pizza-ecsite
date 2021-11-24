package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.service.UserService;



@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public LoginForm setLoginForm() {
		return new LoginForm();
	}
 	
	//ログイン画面に遷移
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	//ログインを行う
	@RequestMapping("/login")
	public String login(LoginForm form) {
		User user = userService.findByPasswordAndMailAddress(form.getEmail(), form.getPassword());
		
		if(user == null) {
			return "login";
		}
		session.setAttribute("user", user);
		return "item_list_pizza";
	}
	
}
