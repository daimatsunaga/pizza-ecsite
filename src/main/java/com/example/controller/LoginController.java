package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.service.UserService;



@Controller
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
	public String login(LoginForm form, Model model) {
		User user = userService.findByPasswordAndMailAddress(form.getEmail(), form.getPassword());
		
		if(user == null) {
			model.addAttribute("loginError", "メールアドレス、またはパスワードが間違っています");
			return "login";
		}
		session.setAttribute("user", user);
		return "item_list_pizza";
	}
	//ログアウトを行う
	@RequestMapping("/logout")
	public String login() {
		session.invalidate();
		return "item_list_pizza";
	}
	
}
