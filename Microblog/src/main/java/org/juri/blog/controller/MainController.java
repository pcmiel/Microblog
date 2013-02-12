package org.juri.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.juri.blog.entity.*;
import org.juri.blog.service.*;
import org.juri.blog.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/")
public class MainController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(method = RequestMethod.GET)
	public String getMessage(Model model) {
		List<Post> posts = mainService.getAllPosts();
		model.addAttribute("posts", posts);
		return "showMessages";
	}

	@RequestMapping(value = "/addmessage", method = RequestMethod.GET)
	public ModelAndView showMessage() {
		return new ModelAndView("addMessage", "command", new Post());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addContact(@ModelAttribute("post") Post post,
			BindingResult result) {
		BlogUser user = mainService.getLoggedInUser();
		mainService.addNewPost(post.getNews(), user);
		return "redirect:";
	}
}