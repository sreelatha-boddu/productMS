package com.FA.productMS.service;

import java.util.List;

import com.FA.productMS.dto.ProductDTO;
import com.FA.productMS.exception.ProductException;

public interface ProductService {
	
	public String addProduct(ProductDTO productDTO) throws ProductException;
	
	public String deleteProduct(String id) throws ProductException;
	
	public ProductDTO getProductByName(String name) throws ProductException;
	
	public List<ProductDTO> getProductByCategory(String category) throws ProductException;
	
	public ProductDTO getProductById(String id) throws ProductException;

	public Boolean updateStockOfProd(String prodId, Integer quantity) throws ProductException;
	
	public List<ProductDTO> viewAllProducts() throws ProductException;

}