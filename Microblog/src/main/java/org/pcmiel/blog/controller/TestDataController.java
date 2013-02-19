package org.pcmiel.blog.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.service.MainService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestDataController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(value = "/addtest", method = RequestMethod.POST)
	public String addTestUsers(Model model) {

		mainService.InsertTestDatas();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		model.addAttribute("userName", name);
		return "redirect:login";
	}
}
