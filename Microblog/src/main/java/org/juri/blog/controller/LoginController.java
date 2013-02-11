package org.juri.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.juri.blog.service.MainService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class LoginController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/addTest", method = RequestMethod.POST)
	public String addTestUsers(Model model) {
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		authorities.add("ROLE_ADMIN");
		authorities.add("ROLE_USER1");
		authorities.add("ROLE_USER2");
		mainService.addNewUser("admin", "admin", authorities, true);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();

		model.addAttribute("userName", name);

		return "redirect:login";
	}
}
