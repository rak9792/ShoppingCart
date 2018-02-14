package com.packt.webstore.validator;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.packt.webstore.domain.Product;

@Component
public class UnitsInStockValidator implements Validator
{

	public boolean supports(Class<?> arg0) 
	{
		return Product.class.isAssignableFrom(arg0);
	}

	public void validate(Object target, Errors errors) 
	{
		Product product= (Product) target;
		if(product.getUnitPrice()!=null && new BigDecimal(1000).compareTo(product.getUnitPrice())<=0 && product.getUnitsInStock()>99)
		{
				errors.rejectValue("unitsInStock", "com.packt.webstore.validator.unitsInStockValidator.message");
		}
		
	}
}