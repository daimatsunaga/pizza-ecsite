package com.example.form;
/**
 *ユーザーログイン時のフォームクラス
 * @author daimatsunaga
 */
public class LoginForm {
	
	//メールアドレス
	private String email;
	//パスワード
	private String password;
	//ゲッターセッター
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password + "]";
	}
	

}	
