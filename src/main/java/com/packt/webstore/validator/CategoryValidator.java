package com.packt.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<Category, String> 
{

	List<String> allowedCategories=new ArrayList<String>();
	
	public CategoryValidator()
	{
		allowedCategories.add("Laptops");
		allowedCategories.add("Tablets");
		allowedCategories.add("SmartPhones");
	}
	
	public void initialize(Category arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValid(String arg0, ConstraintValidatorContext arg1) 
	{
		if(allowedCategories.contains(arg0))
			return true;
		else
			return false;
	}

}
