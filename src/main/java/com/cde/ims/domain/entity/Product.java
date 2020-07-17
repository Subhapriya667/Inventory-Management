package com.cde.ims.domain.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * InventoryManagement
 * com.cde.ims.domain.entity | Product.java
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

@Entity
@Table(name="products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "description")
	private String description;

	@Column(name = "product_price")
	private String productPrice;
	
	@Column(name = "product_quantity")
	private String productQuantity;
	
	@Column(name = "created_date")
	private OffsetDateTime createdDate;

	@Column(name = "updated_date")
	private OffsetDateTime updatedDate;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public OffsetDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(OffsetDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public OffsetDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(OffsetDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate
				+ "]";
	}

	public Product() {

	}

	public Product(long productId, String productName, OffsetDateTime createdDate, OffsetDateTime updatedDate,
			String productPrice, String productQuantity, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.description = description;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}

	

	
}
