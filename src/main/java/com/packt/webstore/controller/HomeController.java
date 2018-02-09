package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping//only one default request mapping allowed per controller
	public String welcome(Model model,RedirectAttributes redirectAttributes)
	{
		model.addAttribute("greeting", "Welcome to the webstore!");
		model.addAttribute("tagline", "The one and only amazing webstore");
		redirectAttributes.addFlashAttribute("greeting", "Welcome to the webstore!");
		redirectAttributes.addFlashAttribute("tagline", "The one and only amazing webstore");
		return "redirect:/welcome/greeting";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting()
	{
		return "welcome";
	}

}