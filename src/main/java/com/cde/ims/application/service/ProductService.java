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
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	private static final String ERRORMSG = "Product not found {}";
	
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Cacheable(value = "products")
	public ProductsList getAllProducts() {
		List<Product> productsList = productRepository.findAll();
		logger.info("Retrieved all products :: {}", productsList);
		return new ProductsList().productList(productsList);
	}
	
	@CachePut(value = "products", key = "#productRequest")
	public Product createProduct(Product productRequest) throws ProductExistException {
		Optional<Product> productObject = productRepository.findById(productRequest.getProductId());
		if (productObject.isPresent()) {
			logger.error("Product already exist for the product {}", productRequest.getProductId());
			throw new ProductExistException(String.valueOf(productRequest.getProductId()));
		} else {
			productRepository.save(productRequest);
			logger.info("Successfully Created the Product :: {} ", productRequest);
		}
		return productRequest;
	}
	
	@Cacheable(value = "products", key = "#productId")
	public Product getProduct(String productId) throws ProductNotFoundException {
		Product product = null;
		Optional<Product> productObject = productRepository.findById(Long.valueOf(productId));
		if (productObject.isPresent()) {
			product = productObject.get();
			logger.info("Successfully Retrieved Product for the Product id{}", productId);
		} else {
			logger.error(ERRORMSG, productId);
			 throw new ProductNotFoundException(productId); 
		}
		return product;
	}

	@CachePut(value = "products", key = "#productRequest")
	public Product updateProduct(Product productRequest) throws ProductNotFoundException {
		Optional<Product> productObject = productRepository.findById(productRequest.getProductId());
		if (productObject.isPresent()) {
			productRepository.save(productRequest);
			logger.info("Successfully updated the Product :: {}", productRequest);
		} else {
			logger.error(ERRORMSG, productRequest.getProductId());
			throw new ProductNotFoundException(String.valueOf(productRequest.getProductId()));
		}
		return productRequest;
	}

	@CacheEvict(value = "products", key = "#productId")
	public String deleteProduct(String productId) throws ProductNotFoundException {
		Optional<Product> productObject = productRepository.findById(Long.valueOf(productId));
		if (productObject.isPresent()) {
			productRepository.deleteById(Long.valueOf(productId));
			logger.info("Successfully Deleted the Product :: {}", productId);
		} else {
			logger.error(ERRORMSG, productId);
			throw new ProductNotFoundException(String.valueOf(productId));
		}
		return "Product Deleted Successfully!";
	}
	
	

}
