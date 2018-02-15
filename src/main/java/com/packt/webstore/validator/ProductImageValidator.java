package com.packt.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

@Component
public class ProductImageValidator implements Validator {

	private long allowedSize;
	
	public ProductImageValidator() 
	{
		this.allowedSize=10240;
	}
	
	@Override
	public boolean supports(Class<?> arg0) 
	{
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) 
	{
		Product product=(Product) arg0;
		if(product.getProductImage()!=null && (product.getProductImage().getSize()>allowedSize))
		{
			arg1.rejectValue("productImage","com.packt.webstore.validator.productImage.message");
		}
	}

}
