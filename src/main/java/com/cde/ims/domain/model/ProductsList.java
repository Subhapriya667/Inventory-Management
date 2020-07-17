package com.cde.ims.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.cde.ims.domain.entity.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * InventoryManagement
 * com.cde.ims.domain.model | ProductList.java
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


public class ProductsList implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	@Valid
	private List<Product> products = null;
	
	public ProductsList productList(List<Product> productList) {
		this.products = productList;
		return this;
	}

	public ProductsList addProducts(Product product) {
		if (this.products == null) {
			products = new ArrayList();
		}
		this.products.add(product);
		return this;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductsList [products=" + products + "]";
	}
	
}
