package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping//only one default request mapping allowed per controller
	public String welcome(Model model)
	{
		model.addAttribute("greeting", "Welcome to the webstore!");
		model.addAttribute("tagline", "The one and only amazing webstore");
		return "welcome";
	}

}