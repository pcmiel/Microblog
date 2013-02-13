package org.juri.blog.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.juri.blog.entity.BlogUser;
import org.juri.blog.entity.Post;
import org.juri.blog.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/")
public class FollowController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String followUsers(Model model) {
		BlogUser user = mainService.getLoggedInUser();
		List<BlogUser> following = mainService.getFollowing(user.getUsername());
		model.addAttribute("following", following);
		List<BlogUser> unfollowing = mainService.getUnfollowing(user.getUsername());
		model.addAttribute("unfollowing", unfollowing);
		return "follow";
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public String unfollowUsers(@RequestParam("username") String unfollow) {
		BlogUser user = mainService.getLoggedInUser();
		mainService.removeFollowing(user.getUsername(), unfollow);
		return "redirect:follow";
	}
	
	@RequestMapping(value = "/followNew", method = RequestMethod.GET)
	public String followNewUsers(@RequestParam("username") String followNew) {
		BlogUser user = mainService.getLoggedInUser();
		mainService.addFollowing(user.getUsername(), followNew);
		return "redirect:follow";
	}
}
