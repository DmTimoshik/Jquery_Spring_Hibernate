package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import dao.User;
import service.RoleDaoService;
import service.UserDaoService;

public class Utils {
	
	
	public User transformation(Template template){
		
		
		User user = new User();
		user.setLogin(template.getLogin());
		user.setPassword(template.getPassword());
		user.setEmail(template.getEmail());
		user.setFirstName(template.getFirstName());
		user.setLastName(template.getLastName());

		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		try {
			date = format.parse(template.getBirthday());
			user.setBirthday(new java.sql.Date(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}


}
