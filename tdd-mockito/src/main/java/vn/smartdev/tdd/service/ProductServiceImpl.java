package vn.smartdev.tdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.smartdev.tdd.dao.reponsitory.ProductRepository;
import vn.smartdev.tdd.model.Category;
import vn.smartdev.tdd.model.Product;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl(){	
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Float getTotalAmountOfProduct(String productId) {
		float sumPrice =0f;
		Product product = productRepository.findById(productId);
		if(Category.SamSung.equals(product.getCatagory()) && product.getTotal() >=5){
			sumPrice = product.getPrice() * product.getTotal() * 0.9f;
			System.out.println("Sum is : "+sumPrice);
		} else if(Category.Apple.equals(product.getCatagory()) && product.getTotal() >=10){
			sumPrice = product.getPrice() * product.getTotal() * 0.8f;
		}
		return sumPrice;
	}

	public List<Product> getAllProductByCategory(Category categoty) {
		List<Product> products = productRepository.findAllProductByCategory(categoty);
		return products;
	}

	public void deleteAllProductWithCategory(Category category) {
		if(Category.SamSung.equals(category)){
			List<Product> products = productRepository.findAllProductByCategory(category);
			products.removeAll(products);
		} else {
			System.out.println("Category is wrong !");
		}
	}

}
