package org.pcmiel.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.pcmiel.blog.dao.*;
import org.pcmiel.blog.entity.*;
import org.pcmiel.blog.service.*;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/")
public class MainController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(method = RequestMethod.GET)
	public String getMessage(Model model) {
		List<Post> posts = mainService.getFollowingPosts();
		model.addAttribute("posts", posts);
		return "allMessages";
	}	

	@RequestMapping(value = "/myMessages", method = RequestMethod.GET)
	public String removeMessage(Model model) {
		List<Post> posts = mainService.getMyPosts();
		model.addAttribute("posts", posts);
		return "myMessages";
	}

	@RequestMapping(value = "/removeMessage", method = RequestMethod.GET)
	public String followNewUsers(@RequestParam("messageId") int id) {
		mainService.removePost(id);
		return "redirect:myMessages";
	}
}