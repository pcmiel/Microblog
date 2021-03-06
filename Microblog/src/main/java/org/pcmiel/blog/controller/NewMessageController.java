package org.pcmiel.blog.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.pcmiel.blog.service.PostService;
import org.pcmiel.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/newMessage")
public class NewMessageController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "postService")
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView newMessage() {
		return new ModelAndView("newMessage", "post", new Post());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addNewMessage(@Valid @ModelAttribute("post") Post post,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newMessage";
		}
		BlogUser user = userService.getLoggedInUser();
		postService.addNewPost(post.getNews(), user);
		return "redirect:";
	}
}