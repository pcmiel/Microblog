package org.pcmiel.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.pcmiel.blog.entity.Post;
import org.pcmiel.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/")
public class MainController {

	@Resource(name = "postService")
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public String showFollowingMessages(Model model) {
		List<Post> posts = postService.getFollowingPosts();
		model.addAttribute("posts", posts);
		return "allMessages";
	}

	@RequestMapping(value = "/myMessages", method = RequestMethod.GET)
	public String showMyMessages(Model model) {
		List<Post> posts = postService.getMyPosts();
		model.addAttribute("posts", posts);
		return "myMessages";
	}

	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST)
	public String removeMyMessage(@RequestParam("messageId") int id) {
		postService.removePost(id);
		return "redirect:myMessages";
	}
}