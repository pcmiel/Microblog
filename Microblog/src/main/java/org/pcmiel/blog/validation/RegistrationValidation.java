package org.pcmiel.blog.validation;

import org.pcmiel.blog.entity.BlogUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("registrationValidator")
public class RegistrationValidation {

	public boolean supports(Class<?> klass) {
		return BlogUser.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors, Boolean usernameExist) {
		BlogUser user = (BlogUser) target;
		if(usernameExist){
			errors.rejectValue("username", "user.username.exist");
		}
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		if (!password.equals(confirmPassword)) {
			errors.rejectValue("confirmPassword", "user.confirmPassword");
		}
	}
}
