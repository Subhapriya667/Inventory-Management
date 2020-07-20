package com.cde.ims.application.controller;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cde.ims.application.service.ProductService;
import com.cde.ims.domain.entity.Product;
import com.cde.ims.domain.model.ProductsList;
import com.cde.ims.infrastructure.exception.ProductExistException;
import com.cde.ims.infrastructure.exception.ProductNotFoundException;

@SpringBootTest
@RunWith(PowerMockRunner.class)
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
	Product product = new Product();
	
	String msg;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		product.setProductId(1);
		product.setProductName("Loreal");
		product.setProductPrice("380");
		product.setProductQuantity("200ml");
		product.setDescription("Smooth Revival");
		product.setCreatedDate(OffsetDateTime.now());		
		product.setUpdatedDate(OffsetDateTime.now());
		
		productController = new ProductController(productService);
		
		msg = "Deleted Success";
		
	}
	
	@Test
	public void createProductTest() throws ProductExistException{
		
		Mockito.when(productService.createProduct(product)).thenReturn(product);
		productController.createProduct(product);
		verify(productService, timeout(1)).createProduct(product);
		
	}
	
	@Test
	public void getProductByIdTest() throws ProductNotFoundException{
		
		Mockito.when(productService.getProduct("1")).thenReturn(product);
		productController.getProductById("1");
		verify(productService, timeout(1)).getProduct("1");
		
	}
	
	
	@Test
	public void updateProductTest() throws ProductNotFoundException{
		Mockito.when(productService.updateProduct(product)).thenReturn(product);
		productController.updateProduct(product);
		verify(productService, timeout(1)).updateProduct(product);
			
	}
	
	@Test
	public void deleteProductTest() throws ProductNotFoundException{		
		Mockito.when(productService.deleteProduct("1")).thenReturn(msg);
		productController.deleteProduct("1");
		verify(productService, timeout(1)).deleteProduct("1");
		
	}
	
	@Test
	public void getAllProductsTest() {
		ProductsList productsList = new ProductsList();
		List<Product> list = new ArrayList<Product>();
		list.add(product);
		productsList.productList(list);
		Mockito.when(productService.getAllProducts()).thenReturn(productsList);
		productController.getAllProducts();
		verify(productService, timeout(1)).getAllProducts();
	}

	
	
	
}
