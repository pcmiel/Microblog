package org.pcmiel.blog.controller;

import javax.annotation.Resource;

import org.pcmiel.blog.service.TestDataService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestDataController {

	@Resource(name = "testDataService")
	private TestDataService testDataService;

	@RequestMapping(value = "/addtest", method = RequestMethod.POST)
	public String insertTestData(Model model) {
		testDataService.InsertTestDatas();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		model.addAttribute("userName", name);
		return "redirect:login";
	}
}
