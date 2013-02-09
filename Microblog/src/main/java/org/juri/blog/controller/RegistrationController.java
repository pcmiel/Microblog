package org.juri.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.juri.blog.entity.*;
import org.juri.blog.service.MainService;
import org.juri.blog.validation.*;
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
	
	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegisterForm(ModelMap model) {
         return new ModelAndView("register", "user", new BlogUser());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute("user") BlogUser user,
			BindingResult result) {
		Boolean usernameExist = mainService.checkIfUsernameExist(user.getUsername());
		registrationValidation.validate(user, result, usernameExist);
		if (result.hasErrors()) {
			return "register";
		}
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		mainService.addNewUser(user.getUsername(), user.getPassword(), authorities, true);
		return "login";
	}

}
