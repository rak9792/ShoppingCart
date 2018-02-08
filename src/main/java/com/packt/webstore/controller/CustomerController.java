package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customers")
	public String clist(Model model)
	{
		/*public ModelAndView clist(){
		 * ModelAndView modelAndView=new ModelAndView();
		 * modelAndView.addObject("customer",customerService.getAllCustomers());
		 * modelAndView.setViewName("customer");
		 * return modelAndView();
		 * }
		 * */
		
		
		model.addAttribute("customer", customerService.getAllCustomers());
		return "customer";
	}
	
	@RequestMapping(value="/customers/add", method=RequestMethod.GET)
	public String addCustomers(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute("newCustomer", customer);
		return "addCustomer";
	}
	
	@RequestMapping(value="/customers/add", method=RequestMethod.POST)
	public String serviceAddCustomers(@ModelAttribute("newCustomer") Customer newCustomer)
	{
		customerService.addCustomer(newCustomer);
		return "redirect:/customers";
	}
}
