package org.juri.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.juri.blog.entity.*;
import org.juri.blog.service.*;
import org.juri.blog.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
// @RequestMapping("/main")
public class MainController {

	// protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(value = "/login")
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

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String getMessage(Model model) {

		// logger.debug("Received request to show all messages");

		List<Post> post = mainService.getAllPosts();

		model.addAttribute("post", post);

		return "showMessages";
	}

	@RequestMapping(value = "/addmessage", method = RequestMethod.GET)
	public ModelAndView showMessage() {
		return new ModelAndView("addMessage", "command", new Post());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("post") Post post,
			BindingResult result) {

		System.out.println("First Name:" + post.getTitle() + "Last Name:"
				+ post.getNews());
		mainService.addNewPost(post.getTitle(), post.getNews(), 0);

		return "redirect:message";
	}

	@RequestMapping(value = "/addTest", method = RequestMethod.POST)
	public String addTestUsers(Model model) {
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		authorities.add("ROLE_ADMIN");
		authorities.add("ROLE_USER1");
		authorities.add("ROLE_USER2");
		mainService.addNewUser("admin", "admin", authorities, true);
		
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    //String name = user.getUsername();
	    
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	 
	    
		model.addAttribute("userName", name);
		
		return "redirect:login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerNewUser(){
		return "register";
	}
}