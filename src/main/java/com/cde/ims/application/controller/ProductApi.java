package com.cde.ims.application.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cde.ims.domain.entity.Product;
import com.cde.ims.domain.model.ProductsList;
import com.cde.ims.infrastructure.exception.ProductExistException;
import com.cde.ims.infrastructure.exception.ProductNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * InventoryManagement
 * com.cde.ims.application.controller | ProductApi.java
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

@Api(value = "Inventory Management System", description = "Operations for product api")
@RequestMapping("/api/v1")
public interface ProductApi {

	
	@ApiOperation(value = "Get all Products", response = ProductsList.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All Products Retrieved Successfully") })
	@GetMapping("/allProducts")
	ResponseEntity<ProductsList> getAllProducts();
	
	@ApiOperation(value = "Create the Product", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Created Successfully") })
	@PostMapping("/product/create")
	ResponseEntity<Product> createProduct(
			@ApiParam(value = "Product details", required = true) @Valid @RequestBody Product product)
			throws ProductExistException;
	
	@ApiOperation(value = "Get the product details for given productId", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Retrieved Successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the Product"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Product Not Found") })
	
	@GetMapping("/product")
	ResponseEntity<Product> getProductById(
			@NotNull @ApiParam(value = "ProductId", required = true) @Valid @RequestParam(value = "productId", required = true) String productId)
			throws ProductNotFoundException;
	
	
	@ApiOperation(value = "Update the Product", response = Product.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Product Updated Successfully") })
	@PutMapping("/product/update")
	ResponseEntity<Product> updateProduct(
			@ApiParam(value = "Product details", required = true) @Valid @RequestBody Product product)
			throws ProductNotFoundException;
	
	@ApiOperation(value = "Delete the Product", response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Product Deleted Successfully") })
	@DeleteMapping("/product/delete")
	ResponseEntity<String> deleteProduct(
			@NotNull @ApiParam(value = "ProductId", required = true) @Valid @RequestParam(value = "productId", required = true) String productId)
			throws ProductNotFoundException;
	
	
	
	
	
}
