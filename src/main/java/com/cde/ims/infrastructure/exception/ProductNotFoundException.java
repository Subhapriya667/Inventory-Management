package com.cde.ims.infrastructure.exception;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	String message = "Product not found for the given id: ";
	String productId;

	public ProductNotFoundException(String productId) {
		super();
		this.message = message + productId;
	}
	
	
}