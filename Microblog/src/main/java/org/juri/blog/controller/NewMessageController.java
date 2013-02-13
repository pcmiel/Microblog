package org.juri.blog.controller;



import javax.annotation.Resource;
import javax.validation.Valid;

import org.juri.blog.entity.*;
import org.juri.blog.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/newMessage")
public class NewMessageController {

	@Resource(name = "mainService")
	private MainService mainService;


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showMessage() {
		return new ModelAndView("newMessage", "post", new Post());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addContact(@Valid @ModelAttribute("post") Post post,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newMessage";
		}
		BlogUser user = mainService.getLoggedInUser();
		mainService.addNewPost(post.getNews(), user);
		return "redirect:";
	}
}