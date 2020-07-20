package com.cde.ims.infrastructure.exception;

public class ProductExistException extends Exception {

	private static final long serialVersionUID = 1L;

	String message = "Product is already exist for the id : {}";
	String productId;

	public ProductExistException(String productrId) {
		super();
		this.message = message + productId;
	}

}
