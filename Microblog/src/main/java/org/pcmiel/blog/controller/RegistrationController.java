package org.pcmiel.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.pcmiel.blog.entity.*;
import org.pcmiel.blog.service.MainService;
import org.pcmiel.blog.service.UserService;
import org.pcmiel.blog.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private RegistrationValidation registrationValidation;
		
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegistrationForm(ModelMap model) {
         return new ModelAndView("register", "user", new BlogUser());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute("user") BlogUser user,
			BindingResult result) {
		Boolean usernameExist = userService.checkThatUserExist(user.getUsername());
		registrationValidation.validate(user, result, usernameExist);
		if (result.hasErrors()) {
			return "register";
		}
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		userService.addNewUser(user.getUsername(), user.getPassword(), authorities, true);
		return "redirect:login";
	}
}
