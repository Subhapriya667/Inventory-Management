package com.cde.ims.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cde.ims.domain.entity.Product;

/**
 * 
 * InventoryManagement
 * com.cde.ims.application.repository | ProductRepository.java
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

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
