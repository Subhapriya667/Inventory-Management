package com.cde.ims.application.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cde.ims.application.repository.ProductRepository;
import com.cde.ims.domain.entity.Product;
import com.cde.ims.domain.model.ProductsList;
import com.cde.ims.infrastructure.exception.ProductExistException;
import com.cde.ims.infrastructure.exception.ProductNotFoundException;
import com.sun.xml.bind.v2.TODO;

/**
 * 
 * InventoryManagement
 * com.cde.ims.application.service | ProductService.java
 * -------------------------------------------------------------------------
 * 
 *
 * @author Subhapriya M
 * @version 1.0
 * 
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 	Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * -------------------------------------------------------------------------
 * 1    --					 Jul 15, 2020			-		Initial version
 * 
 * </pre>
 */
@Service
public class ProductService {
	
	/** Inject Logger **/
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	//Caching using redis cache
	@Cacheable(value = "products")
	public ProductsList getAllProducts() {
		List<Product> productsList = productRepository.findAll();
		logger.info("Retrieved all products :: ", productsList);
		return new ProductsList().productList(productsList);
	}
	//@Cacheput used to store products data in local which create newly.
	@CachePut(value = "products", key = "#productRequest")
	public Product createProduct(Product productRequest) throws ProductExistException {
		Optional<Product> productObject = productRepository.findById(productRequest.getProductId());
		// check if product is already exists. 
		// if yes, throws an exception or else save as new product in DB.
		if (productObject.isPresent()) {
			logger.error("Product already exist : ", productRequest.getProductId());
			throw new ProductExistException(String.valueOf(productRequest.getProductId()));
		} else {
			productRepository.save(productRequest);
			logger.info("Successfully Created the Product:: ", productRequest);
		}
		return productRequest;
	}
	
	@Cacheable(value = "products", key = "#productId")
	public Product getProduct(String productId) throws ProductNotFoundException {
		Product product = null;
		Optional<Product> productObject = productRepository.findById(Long.valueOf(productId));
		// check if product is already exists. 
				// if yes, continue the process or else throw product not found exception.
		if (productObject.isPresent()) {
			product = productObject.get();
			logger.info("Successfully Retrieved Product for the Product id " + productId, productId);
		} else {
			logger.error("Product for the id "+productId +" is not found", productId);
			 throw new ProductNotFoundException(productId); 
			//TODO - why no try block?
		}
		return product;
	}

	@CachePut(value = "products", key = "#productRequest")
	public Product updateProduct(Product productRequest) throws ProductNotFoundException {
		Optional<Product> productObject = productRepository.findById(productRequest.getProductId());
		// check if product is already exists. 
			// if yes, continue the process to update product in DB or else throw product not found exception.
		if (productObject.isPresent()) {
			productRepository.save(productRequest);
			logger.info("Successfully updated the Product : ", productRequest);
		} else {
			logger.error("Product for the id "+productRequest.getProductId() +"not found", productRequest.getProductId());
			throw new ProductNotFoundException(String.valueOf(productRequest.getProductId()));
		}
		return productRequest;
	}

	@CacheEvict(value = "products", key = "#productId")
	public String deleteProduct(String productId) throws ProductNotFoundException {
		Optional<Product> productObject = productRepository.findById(Long.valueOf(productId));
		// check if product is already exists. 
			// if yes, continue the process to delete the product in DB or else throw product not found exception.
		if (productObject.isPresent()) {
			productRepository.deleteById(Long.valueOf(productId));
			logger.info("Successfully Deleted the Product :: {}", productId);
		} else {
			logger.error("Product for the id "+ productId +"not found", productId);
			throw new ProductNotFoundException(String.valueOf(productId));
		}
		return "Product Deleted Successfully!";
	}
	
	

}
