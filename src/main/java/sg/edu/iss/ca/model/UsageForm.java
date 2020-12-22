package sg.edu.iss.ca.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UsageForm {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private int id;
	private String customer;
	private String car;
	private String description;
	private String creationDate;
	private boolean submitted;
	@OneToMany(mappedBy="usageForm")
	private List<FormCart> formCartList;
	
//	@ManyToOne
//	private User user;
	
	public UsageForm() {
		super();
		submitted = false;
	}
	public UsageForm(String customer, String car, String description, String creationDate, boolean submitted) {
		super();
		this.customer = customer;
		this.car = car;
		this.description = description;
		this.creationDate = creationDate;
		this.submitted = submitted;
	}
	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<FormCart> getFormCartList() {
		return formCartList;
	}

	public void setFormCartList(List<FormCart> formCartList) {
		this.formCartList = formCartList;
	}
	
	public boolean isSubmitted() {
		return submitted;
	}
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}


}
