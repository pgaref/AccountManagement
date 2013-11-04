package db.integration;
import java.sql.Timestamp;

public class Customer {
	private Integer ID;
	private Timestamp regDate;
	private Timestamp expDate;
	private String domain;
	private String email;
	private String mobile;
	private Integer charge;
	private String comments;
	private Timestamp lastUpdate;
	
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public Timestamp getExpDate() {
		return expDate;
	}
	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}
	public Integer getCharge() {
		return charge;
	}
	public void setCharge(Integer charge) {
		this.charge = charge;
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
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	
	public String toString() {
		return ("ID: " + this.getID() + ", RegDate: " + this.getRegDate()
				+ ", ExpDate: " + this.getExpDate() + ", Domain: "
				+ this.getDomain() + ", email: " + this.getEmail()
				+ ", Mobile: " + this.getEmail() + ", Charge: "
				+ this.getCharge() + ", Updated: " + this.getLastUpdate());
	}

	
}
