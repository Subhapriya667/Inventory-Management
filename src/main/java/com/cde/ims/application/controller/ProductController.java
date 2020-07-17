package com.cde.ims.application.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cde.ims.application.service.ProductService;
import com.cde.ims.domain.entity.Product;
import com.cde.ims.domain.model.ProductsList;
import com.cde.ims.infrastructure.exception.ProductExistException;
import com.cde.ims.infrastructure.exception.ProductNotFoundException;

import io.swagger.annotations.ApiParam;

/**
 * 
 * InventoryManagement
 * com.cde.ims.application.controller | ProductController.java
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


@RestController
public class ProductController implements ProductApi {

	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private final ProductService productService;

	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Method to create new product
	 * 
	 * @param product
	 *            Model to create product 
	 * @return
	 */

	@Override
	public ResponseEntity<Product> createProduct(@ApiParam(value = "Product details", required = true) @Valid @RequestBody Product product) throws ProductExistException {
		Product productResponse = productService.createProduct(product);
		logger.info("Product Created Successfully : ", product);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	/**
	 * Method to get product using product id
	 * 
	 * @param productId
	 *            Id of the product
	 * @return
	 */
	@Override
	public ResponseEntity<Product> getProductById(@NotNull @ApiParam(value = "ProductId", required =
			  true) @Valid @RequestParam(value = "productId", required = true) String
			  productId) throws ProductNotFoundException {
		Product productResponse = productService.getProduct(productId);
		logger.info("Product Retrieved Successfully : ", productResponse); 
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	/**
	 * Method to update values of the existing product
	 * 
	 * @param product
	 *            Model to update product
	 * @return
	 */
	@Override
	public ResponseEntity<Product> updateProduct(@ApiParam(value = "Product details", required = true) @Valid @RequestBody Product product) throws ProductNotFoundException {
		Product productResponse = productService.updateProduct(product);
		logger.info("Product Updated Successfully : ", product);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	/**
	 * Method to delete product using product id
	 * 
	 * @param productId
	 *            Id of the product
	 * @return
	 */
	@Override
	public ResponseEntity<String> deleteProduct(@NotNull @ApiParam(value = "ProductId", required = true) @Valid @RequestParam(value = "productId", required = true) String productId) throws ProductNotFoundException {
		logger.info("Process to delete the pro");
		String productResponse = productService.deleteProduct(productId);
		logger.info("Product Deleted Successfully for the id : ", productId);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}	

	/**
	 * Method to get all products
	 * 
	 * 
	 * @return
	 */
	@Override
	public ResponseEntity<ProductsList> getAllProducts() {
		ProductsList productsList = productService.getAllProducts();
		logger.info("successfully retrieved all products :: {}", productsList);
		return new ResponseEntity<>(productsList, HttpStatus.OK);
	}	

	
	
}
