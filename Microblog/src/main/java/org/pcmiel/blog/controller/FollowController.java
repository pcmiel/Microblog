package org.pcmiel.blog.controller;

import java.util.List;
import javax.annotation.Resource;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.service.FollowService;
import org.pcmiel.blog.service.MainService;
import org.pcmiel.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/")
public class FollowController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "followService")
	private FollowService followService;

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String showUsers(Model model) {
		BlogUser user = userService.getLoggedInUser();
		List<BlogUser> following = followService.getFollowing(user.getUsername());
		model.addAttribute("following", following);
		List<BlogUser> unfollowing = followService.getUnfollowing(user.getUsername());
		model.addAttribute("unfollowing", unfollowing);
		return "follow";
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public String removeFollowingUsers(@RequestParam("username") String unfollow) {
		BlogUser user = userService.getLoggedInUser();
		followService.removeFollowing(user.getUsername(), unfollow);
		return "redirect:follow";
	}

	@RequestMapping(value = "/followNew", method = RequestMethod.POST)
	public String addFollowingUsers(@RequestParam("username") String followNew) {
		BlogUser user = userService.getLoggedInUser();
		followService.addFollowing(user.getUsername(), followNew);
		return "redirect:follow";
	}
}
