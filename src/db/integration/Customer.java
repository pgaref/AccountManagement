package db.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Customer {
	private int ID;
	private String name;
	private Date regDate;
	private Date expDate;
	private String domain;
	private String email;
	private String mobile;
	private Integer charge;
	private String comments;
	private Date lastUpdate;
	private String new_Date="";
	private int how_long=32;
	private int notifications;
	private int new_charge;
	private boolean exist;
	private int month;
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
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
	public Integer getId() {
		return ID;
	}
	public void setId(Integer iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name=name;
		
	}
	public String getName() {
		return this.name;
		
	}
	
	public String toString() {
		return ("ID: " + this.getID() +",Name:"+this.name+ ", RegDate: " + this.getRegDate()
				+ ", ExpDate: " + this.getExpDate() + ", Domain: "
				+ this.getDomain() + ", email: " + this.getEmail()
				+ ", Mobile: " + this.getEmail() + ", Charge: "
				+ this.getCharge() + ", Updated: " + this.getLastUpdate());
	}
	public void setExpDate_input(String output) {
		
		this.setNew_Date(output);
	}
	public String getNew_Date() {
		return new_Date;
	}
	public void setNew_Date(String new_Date) {
		this.new_Date = new_Date;
	}
	public int getHow_long() {
		return how_long;
	}
	public void setHow_long(int how_long) {
		this.how_long = how_long;
	}

	public int getNotifications() {
		return notifications;
	}
	public void setNotifications(int notifications) {
		this.notifications = notifications;
	}
	public int getNew_charge() {
		return new_charge;
	}
	public void setNew_charge(int new_charge) {
		this.new_charge = new_charge;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}	
	
	

	
}
