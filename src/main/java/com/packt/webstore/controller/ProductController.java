package com.packt.webstore.controller;

import com.packt.webstore.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Controller
@RequestMapping("market")//Relative Request Mapping
public class ProductController {

	
	
	@Autowired
	private ProductService productService;
	
	
		@RequestMapping("/product")
		public String getProductById(@RequestParam("id") String productId,Model model)
		{
			model.addAttribute("product", productService.getProductById(productId) );
			return "product";
		}
	
		@RequestMapping("/products")
		public String list(Model model)
		{
			model.addAttribute("products", productService.getAllProducts());
			return "products";
		}
		
		@RequestMapping("/update/stock")
		public String updateStock(Model model)
		{
			productService.updateAllStock();
			return "redirect:/market/products";
		}
		
		@RequestMapping("/products/{category}")
		public String getProductsByCategory(Model model,@PathVariable("category") String productCategory)
		//if we use only @PathVariable String productCategory ,it will try to look for {productCategory}
		//in RequestMapping.
		{
			model.addAttribute("products", productService.getProductsByCategory(productCategory));
			return "products";
		}
		
		@RequestMapping("/products/filter/{params}")
		public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String,List<String>> filterParams,Model model)
		{
			model.addAttribute("products", productService.getProductsByFilter(filterParams));
			return "products";
		}
		
		@RequestMapping("/products/{category}/{price}")
		public String getProductsByAnotherFilter(@PathVariable String category,@RequestParam("brand") String manufacturer,@MatrixVariable(pathVar="price") Map<String,Object> filterParams ,Model model)
		{
			
			model.addAttribute("products",productService.getProductsByAnotherFilter(category,manufacturer,filterParams));
			return "products";
		}
		
		@InitBinder
		public void initialiseBinder(WebDataBinder binder)
		{
			binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock","condition");
		}
		
		@RequestMapping(value="/products/add", method=RequestMethod.GET)
		public String getAddNewProductForm(Model model)
		{
			Product newProduct=new Product();
			model.addAttribute("newProduct", newProduct);
			return "addProduct";
		}
		
		@RequestMapping(value="/products/add", method=RequestMethod.POST)
		public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result)
		{
			String[] suppressedFields=result.getSuppressedFields();
			if(suppressedFields.length>0)
			{
				throw new RuntimeException("Attempting to bind disallowed fields:" + StringUtils.arrayToCommaDelimitedString(suppressedFields) );
			}
			productService.addProduct(newProduct);
			return "redirect:/market/products";
		}
}