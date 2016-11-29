package vn.smartdev.tdd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.smartdev.tdd.model.Category;
import vn.smartdev.tdd.model.Product;

public class ProductServiceImplTest {

	protected Map<Category, List<Product>> products = new HashMap<Category, List<Product>>();

	protected void prepareData(){
		products.put(Category.SamSung, createSamsungProducts());
		products.put(Category.Apple, createAppleProducts());
	}

	protected List<Product> createSamsungProducts(){
		List<Product> samsungProducts = new ArrayList<Product>();
		samsungProducts.add(new Product("SS01", "Samsung Galaxy S2", 10, new Float(100), Category.SamSung));
		samsungProducts.add(new Product("SS02", "Samsung Galaxy S4", 5, new Float(10), Category.SamSung));
		samsungProducts.add(new Product("SS03", "Samsung Galaxy S5", 1, new Float(300), Category.SamSung));
		samsungProducts.add(new Product("SS04", "Samsung Galaxy S7", 7, new Float(200), Category.SamSung));
		return samsungProducts;

	}

	private List<Product> createAppleProducts(){
		List<Product> appleProducts = new ArrayList<Product>();
		appleProducts.add(new Product("AP05", "IPhone 5", 100, new Float(400), Category.Apple));
		appleProducts.add(new Product("AP06", "IPhone 6", 50, new Float(500), Category.Apple));
		appleProducts.add(new Product("AP07", "IPhone 7", 10, new Float(600), Category.Apple));
		return appleProducts;
	}
}
