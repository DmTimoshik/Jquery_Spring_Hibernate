package util;

import java.util.Calendar;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EditValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return Template.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "password", "pass.required.password");
		ValidationUtils.rejectIfEmpty(errors, "passwordConfirm", "pass.required.password");	
		ValidationUtils.rejectIfEmpty(errors, "email", "email.required.email"); 
		ValidationUtils.rejectIfEmpty(errors, "firstName", "first.required.firstName");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "last.required.lastName");
		Template templ = (Template)target;
		
		if(!templ.getPassword().equalsIgnoreCase(templ.getPasswordConfirm())){
			errors.rejectValue("passwordConfirm", "pass.again.password");
		}
		
		try {
			if (templ.getBirthday().equalsIgnoreCase("yyyy-mm-dd")
					|| Integer.valueOf(templ.getBirthday().split("-")[1]) < 1
					|| Integer.valueOf(templ.getBirthday().split("-")[1]) > 12
					|| Integer.valueOf(templ.getBirthday().split("-")[2]) < 1
					|| Integer.valueOf(templ.getBirthday().split("-")[2]) > 31
					|| Integer.valueOf(templ.getBirthday().split("-")[0]) > Calendar
							.getInstance().getWeekYear()) {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			errors.rejectValue("birthday", "birth.required.birthday");
		}
	}

}
