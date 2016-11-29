package vn.smartdev.tdd.dao.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.smartdev.tdd.model.Category;
import vn.smartdev.tdd.model.Product;
import vn.smartdev.tdd.model.User;

public interface ProductRepository extends JpaRepository<Product, String>{

	public Product findById(String id);
	public List<Product> findAllProductByCategory(Category category);

}
