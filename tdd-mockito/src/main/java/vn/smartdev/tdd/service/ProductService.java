package vn.smartdev.tdd.service;

import java.util.List;

import vn.smartdev.tdd.model.Category;
import vn.smartdev.tdd.model.Product;

public interface ProductService {

	Float getTotalAmountOfProduct(String productId);
	List<Product> getAllProductByCategory(Category categoty);
	void deleteAllProductWithCategory(Category category);
}
