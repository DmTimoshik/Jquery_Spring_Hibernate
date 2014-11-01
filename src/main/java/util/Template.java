package util;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.RequestParam;

import dao.User;

public class Template {

	private String login;
	private String password;
	private String passwordConfirm;
	private String email;
	private String firstName;
	private String lastName;
	private String birthday;
	private String role;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public void setUserFormater(User user){
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.passwordConfirm = user.getPassword();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.birthday = formatter.format(user.getBirthday());
		this.role = user.getRole().getName();
	}

}
