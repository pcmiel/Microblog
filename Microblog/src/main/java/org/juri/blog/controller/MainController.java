package org.juri.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.juri.blog.entity.Message;
import org.juri.blog.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping("/main")
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "messageService")
	private MessageService messageService;

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String getMessage(Model model) {

		logger.debug("Received request to show all messages");

		List<Message> message = messageService.getAll();

		model.addAttribute("message", message);

		return "showMessages";
	}

	@RequestMapping(value = "/addmessage", method = RequestMethod.GET)
	public ModelAndView showMessage() {
		return new ModelAndView("addMessage", "command", new Message());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("message") Message message,
			BindingResult result) {

		System.out.println("First Name:" + message.getTitle() + "Last Name:"
				+ message.getNews());
		messageService.add(message.getTitle(), message.getNews(), 0);

		return "redirect:message.html";
	}
}