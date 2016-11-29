package vn.smartdev.tdd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import vn.smartdev.tdd.dao.reponsitory.ProductRepository;
import vn.smartdev.tdd.model.Category;
import vn.smartdev.tdd.model.Product;
import vn.smartdev.tdd.service.ProductServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImpl_getTotalPriceOfProductTest extends ProductServiceImplTest{

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldReturn45WhenCatalogIsSammsungAndNumberOfRecordIs10AndPriceIs5(){
		Float expected = new Float(45);
		Float actualResult = prepareGetTotalAmountOfProduct("SS02");
		Assertions.assertThat(actualResult).isEqualTo(expected);
		
	}
	
	@Test
	public void shouldReturnSizeOfListProductIs4WhenFindAllProductByCategoryIsSamSung(){
		int expected = 4;
		
		List<Product> products = createSamsungProducts();
		
		Mockito.when(productService.getAllProductByCategory(Category.SamSung)).thenReturn(products);
		List<Product> result = productService.getAllProductByCategory(Category.SamSung);
		Assertions.assertThat(result.size()).isEqualTo(expected);	
	}
	
	@Test
	public void shouldReturnTrueWhenDeleteAllProductWithCategoryIsSamSung(){
		List<Product> products = createSamsungProducts();
		boolean expected = true;
		Mockito.when(productService.getAllProductByCategory(Category.SamSung)).thenReturn(products);
		boolean  result  = productService.getAllProductByCategory(Category.SamSung).removeAll(products);
		Assertions.assertThat(result).isEqualTo(expected);	 
	}
	
	private Float prepareGetTotalAmountOfProduct(String productId){
		Mockito.when(productRepository.findById("SS02")).thenReturn(new Product("SS02", "SamSung Galaxy S3", 5, new Float(10),Category.SamSung));
		return productService.getTotalAmountOfProduct(productId);
	}
}
