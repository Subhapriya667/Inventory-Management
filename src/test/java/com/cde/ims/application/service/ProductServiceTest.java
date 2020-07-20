package com.cde.ims.application.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cde.ims.application.repository.ProductRepository;
import com.cde.ims.domain.entity.Product;
import com.cde.ims.infrastructure.exception.ProductExistException;
import com.cde.ims.infrastructure.exception.ProductNotFoundException;

@SpringBootTest
@RunWith(PowerMockRunner.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productRepository;

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

		productService = new ProductService(productRepository);

		msg = "Deleted Success";

	}

	@Test
	public void createNewProductTest() throws ProductExistException {
		Optional<Product> productObject = Optional.empty();
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.createProduct(product);
		verify(productRepository, timeout(1)).findById(1l);
	}

	@Test(expected = ProductExistException.class)
	public void createExistsProductTest() throws ProductExistException {
		Optional<Product> productObject = Optional.of(product);
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.createProduct(product);
	}

	@Test
	public void getExistProductTest() throws ProductNotFoundException {
		Optional<Product> productObject = Optional.of(product);
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.getProduct("1");
		verify(productRepository, timeout(1)).findById(1l);
	}

	@Test(expected = ProductNotFoundException.class)
	public void getNotExistProductTest() throws ProductNotFoundException {
		Optional<Product> productObject = Optional.empty();
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.getProduct("1");
	}

	@Test
	public void updateExistProductTest() throws ProductNotFoundException {
		Optional<Product> productObject = Optional.of(product);
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.updateProduct(product);
		verify(productRepository, timeout(1)).findById(1l);
	}

	@Test(expected = ProductNotFoundException.class)
	public void updateNotExistProductTest() throws ProductNotFoundException {
		Optional<Product> productObject = Optional.empty();
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.updateProduct(product);
		verify(productRepository, timeout(1)).findById(1l);
	}

	@Test
	public void deleteExistProductTest() throws ProductNotFoundException {
		Optional<Product> productObject = Optional.of(product);
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.deleteProduct("1");
		verify(productRepository, timeout(1)).deleteById(1l);
	}

	@Test(expected = ProductNotFoundException.class)
	public void deletetExistNoProductTest() throws ProductNotFoundException {
		Optional<Product> productObject = Optional.empty();
		Mockito.when(productRepository.findById(1l)).thenReturn(productObject);
		productService.deleteProduct("1");
		verify(productRepository, timeout(1)).deleteById(1l);
	}
	
	@Test
	public void getAllOrdersTest() {
		List<Product> productsList = new ArrayList<Product>();
		productsList.add(product);
		Mockito.when(productRepository.findAll()).thenReturn(productsList);
		productService.getAllProducts();
		verify(productRepository, timeout(1)).findAll();
	}

}
