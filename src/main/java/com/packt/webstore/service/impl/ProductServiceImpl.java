package com.packt.webstore.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts()
	{
		return productRepository.getAllProducts();
	}
	
	public void updateAllStock()
	{
		List<Product> allProducts=productRepository.getAllProducts();
		for(Product product: allProducts)
		{
			if(product.getUnitsInStock()<500)
			{
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock()+1000);
			}
		}
	}

	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		
		return productRepository.getProductsByCategory(category);
	}

	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}

	public Product getProductById(String productId) 
	{
		return productRepository.getProductById(productId);
	}

	public List<Product> getProductsByAnotherFilter(String category,String manufacturer,Map<String, Object> filterParams) 
	{
		Map<String,Object> map = new HashMap<String,Object>();
	    //fill in map
	    Set<Entry<String, Object>> set = filterParams.entrySet();

	    for(Entry<String,Object> entry : set)
	    {
	        map.put(entry.getKey(), entry.getValue());
	    }
	    map.put("manufacturer",manufacturer);
	    map.put("category",category);
		return productRepository.getProductsByAnotherFilter(map);
	}

	public void addProduct(Product product) 
	{
		productRepository.addProduct(product);
	}
	
	

	
}
