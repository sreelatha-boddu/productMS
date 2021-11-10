package com.FA.productMS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.FA.productMS.entity.Product;

public interface ProductRepo extends CrudRepository<Product, String> {
	
	public Product findByProdId(String id);
	
	public Product findByProductName(String name);
	
	public List<Product> findByCategory(String category);
	
	public List<Product> findAll();

}
