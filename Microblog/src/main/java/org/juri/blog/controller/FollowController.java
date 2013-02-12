package org.juri.blog.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.juri.blog.entity.BlogUser;
import org.juri.blog.entity.Post;
import org.juri.blog.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app/follow")
public class FollowController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(method = RequestMethod.GET)
	public String showUsers(Model model)
	{
		BlogUser user = mainService.getLoggedInUser();
		List<BlogUser> users = mainService.getFollowing(user.getUsername());
		model.addAttribute("users", users);
		return "follow";
	}
}
