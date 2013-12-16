package db.integration;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class SearchBean {

	@NotEmpty
	private String word;
	private String category;
	private List<Customer> customerList;
	
	public SearchBean(){}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
}
