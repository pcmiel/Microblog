package org.juri.blog.controller;

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegisterForm(ModelMap model) {
         return new ModelAndView("register", "user", new BlogUser());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute("user") BlogUser user,
			BindingResult result) {
		registrationValidation.validate(user, result);
		if (result.hasErrors()) {
			return "register";
		}
		return "login";
	}

}
