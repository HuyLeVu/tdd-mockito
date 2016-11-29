package vn.smartdev.tdd.model;

public class Product {

	private String id;
    private String name;
    private Integer total;
    private Float price;
    private Category catagory;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Category getCatagory() {
		return catagory;
	}
	public void setCatagory(Category catagory) {
		this.catagory = catagory;
	}
	public Product(String id, String name, Integer total, Float price, Category catagory) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
		this.price = price;
		this.catagory = catagory;
	}
   
}
