package db.integration;

import java.sql.Timestamp;

public class History {
	private Integer ID;
	private Timestamp delDate;
	private String domain;
	private String email;
	private String delComments;
	
	public Timestamp getDelDate() {
		return delDate;
	}
	public void setDelDate(Timestamp delDate) {
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
	public String getComments() {
		return delComments;
	}
	public void setComments(String comments) {
		this.delComments = comments;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	
	public String toString() {
		return ("ID: " + this.getID() + ", Delete Date: " + this.getDelDate()
				+ ", Domain: " + this.getDomain() + ", email: " + this.getEmail()
				+ ", Delete Comments: " + this.getComments());
	}

	

}
