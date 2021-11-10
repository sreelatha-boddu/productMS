package com.FA.productMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FA.productMS.dto.ProductDTO;
import com.FA.productMS.entity.Product;
import com.FA.productMS.exception.ProductException;
import com.FA.productMS.repository.ProductRepo;
import com.FA.productMS.validator.ValidateProduct;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepository;
	
	//used to create unique product id like "P" + index = P1, P2, P3...
	private static int index;
	
	static {
		index=100;
	}
	
	//method to add product
	@Override
	public String addProduct(ProductDTO productDTO) throws ProductException {
		
		ValidateProduct.validateProduct(productDTO);
		
		Product product = productRepository.findByProductName(productDTO.getProductName());
		
		if(product != null)
			throw new ProductException("Service.PRODUCT_ALREADY_EXISTS");
		
		product = new Product();
		
		String id = "P"+index++;
		
		product.setProdId(id);
		product.setProductName(productDTO.getProductName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setSubCategory(productDTO.getSubCategory());
		product.setSellerId(productDTO.getSellerId());
		product.setProductRating(productDTO.getProductRating());
		product.setStock(productDTO.getStock());
		
		productRepository.save(product);
		
		return product.getProdId();
	}

	//method to get product by product name
	@Override
	public ProductDTO getProductByName(String name) throws ProductException {
		
		Product product = productRepository.findByProductName(name);
		
		if(product == null)
			throw new ProductException("Service.PRODUCT_DOES_NOT_EXISTS");
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		
		return productDTO;
	}
	
	//method to get product by product id
	@Override
	public ProductDTO getProductById(String id) throws ProductException {
		
		Product product = productRepository.findByProdId(id);
		
		if(product == null)
			throw new ProductException("Service.PRODUCT_DOES_NOT_EXISTS");
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		
		return productDTO;
	}

	//method to delete product by product id
	@Override
	public String deleteProduct(String id) throws ProductException {
		
		Product product = productRepository.findByProdId(id);
		
		if(product == null)
			throw new ProductException("Service.CANNOT_DELETE_PRODUCT");
		
		productRepository.delete(product);
		
		return "Product successfully deleted";
		
	}

	//method to get product by category
	@Override
	public List<ProductDTO> getProductByCategory(String category) throws ProductException {
		
		List<Product> list = productRepository.findByCategory(category);
		
		if(list.isEmpty())
			throw new ProductException("Service.CATEGORY_ERROR");
		
		List<ProductDTO> li = new ArrayList<>();
		
		for(Product product : list)
		{
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setProdId(product.getProdId());
			productDTO.setCategory(product.getCategory());
			productDTO.setDescription(product.getDescription());
			productDTO.setImage(product.getImage());
			productDTO.setPrice(product.getPrice());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductRating(product.getProductRating());
			productDTO.setSellerId(product.getSellerId());
			productDTO.setStock(product.getStock());
			productDTO.setSubCategory(product.getCategory());
			
			li.add(productDTO);
		}
		
		return li;
	}

	//method to update stock
	@Override
	public Boolean updateStockOfProd(String prodId, Integer quantity) throws ProductException {
		Optional<Product> optProduct = productRepository.findById(prodId);
		Product product = optProduct.orElseThrow(()-> new ProductException("Product does not exist"));
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			return true;
		}
		return false;		
	}

	//method to view all the products
	@Override
	public List<ProductDTO> viewAllProducts() throws ProductException {
		
		List<Product> list = productRepository.findAll();
		
		if(list.isEmpty())
			throw new ProductException("There are no products to be shown");
		
		List<ProductDTO> li = new ArrayList<>();
		
		list.forEach(l -> {
			ProductDTO prod = new ProductDTO();
			prod.setCategory(l.getCategory());
			prod.setDescription(l.getDescription());
			prod.setImage(l.getImage());
			prod.setPrice(l.getPrice());
			prod.setProdId(l.getProdId());
			prod.setProductName(l.getProductName());
			prod.setProductRating(l.getProductRating());
			prod.setSellerId(l.getSellerId());
			prod.setStock(l.getStock());
			prod.setSubCategory(l.getSubCategory());
			
			li.add(prod);
		});
		
		return li;
	}


}
