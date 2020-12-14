package sg.edu.iss.ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	private String partNumber;
	private String name;
	private String description;
	private String manufacturer;
	private String color;
	private String dimension;
	private String category;
	private String subCategory;
	private String type;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String partNumber, String name, String description, String manufacturer, String color,
			String dimension, String category, String subCategory, String type) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
		this.color = color;
		this.dimension = dimension;
		this.category = category;
		this.subCategory = subCategory;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}