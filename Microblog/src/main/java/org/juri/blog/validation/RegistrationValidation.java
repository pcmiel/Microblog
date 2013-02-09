package org.juri.blog.validation;

import org.juri.blog.entity.BlogUser;
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
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
		// "Size.user.username");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		// "degree.required");
		if(usernameExist){
			errors.rejectValue("username", "user.username.exist");
		}
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		if (!password.equals(confirmPassword)) {
			errors.rejectValue("confirmPassword", "user.confirmPassword");
		}
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
		// "Size.validationForm.userName",
		// "User Name must not be Empty.");
		// String username = user.getUsername();
		// if ((username != null) && ((username.length()) > 50)) {
		// errors.rejectValue("username",
		// "name.required",
		// "User Name must not more than 50 characters.");
		// }
		// String password = user.getPassword();
		// errors.rejectValue("password", "password.required");
		// if (!(registration.getPassword()).equals(registration
		// .getConfirmPassword())) {
		// errors.rejectValue("password",
		// "matchingPassword.registration.password",
		// "Password and Confirm Password Not match.");
		// }
	}
}
