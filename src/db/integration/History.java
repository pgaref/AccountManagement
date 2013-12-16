package db.integration;

import java.sql.Timestamp;
import java.util.Date;

public class History {
	private Integer id;
	private String name;
	private Date delDate;
	private String domain;
	private String email;
	private String mobile;
	private String delComments;
	private String new_Date;
	
	public History(){
		
	}
	public History(Integer id, String name, String domain, Date delDate,
			String delComments, String email, String mobile) {
		this.id=id;
		this.name=name;
		this.domain=domain;
		this.email=email;
		this.delDate=delDate;
		this.mobile=mobile;
		this.setDelComments(delComments);
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer iD) {
		id = iD;
	}
	
	public String toString() {
		return ("ID: " + this.getId() + ", Delete Date: " + this.getDelDate()
				+ ", Domain: " + this.getDomain() + ", email: " + this.getEmail()
				+ ", Delete Comments: " + this.getDelComments());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setDelDate_input(String output) {
		this.new_Date = output;
		
	}
	public String getDelComments() {
		return delComments;
	}
	public void setDelComments(String delComments) {
		this.delComments = delComments;
	}
	public String getNew_Date() {
		return new_Date;
	}
	public void setNew_Date(String new_Date) {
		this.new_Date = new_Date;
	}

	

}
