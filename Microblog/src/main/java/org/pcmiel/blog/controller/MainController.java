package org.pcmiel.blog.controller;

import java.util.List;
import javax.annotation.Resource;
import org.pcmiel.blog.entity.*;
import org.pcmiel.blog.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/")
public class MainController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping(method = RequestMethod.GET)
	public String showFollowingMessages(Model model) {
		List<Post> posts = mainService.getFollowingPosts();
		model.addAttribute("posts", posts);
		return "allMessages";
	}	

	@RequestMapping(value = "/myMessages", method = RequestMethod.GET)
	public String showMyMessages(Model model) {
		List<Post> posts = mainService.getMyPosts();
		model.addAttribute("posts", posts);
		return "myMessages";
	}

	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST)
	public String removeMyMessage(@RequestParam("messageId") int id) {
		mainService.removePost(id);
		return "redirect:myMessages";
	}
}