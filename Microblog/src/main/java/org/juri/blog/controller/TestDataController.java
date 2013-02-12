package org.juri.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.juri.blog.service.MainService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class TestDataController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(value = "/addtest", method = RequestMethod.POST)
	public String addTestUsers(Model model) {

		mainService.InsertTestDatas();
		// List<String> authorities = new ArrayList<String>();
		// authorities.add("ROLE_USER");
		// mainService.addNewUser("admin", "admin", authorities, true);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();

		model.addAttribute("userName", name);

		return "redirect:login";
	}
}
