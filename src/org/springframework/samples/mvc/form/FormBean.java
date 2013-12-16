package org.springframework.samples.mvc.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.samples.mvc.convert.MaskFormat;

import db.integration.Customer;
import db.integration.CustomerJDBCTemplate;

import javax.validation.constraints.Size;

public class FormBean {
	
	private int id;
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String domain;
	
	private String email;
	
	private int age;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    @NotNull 
	private Date birthDate;

	private String phone;

	@NumberFormat(pattern="$###,###.00")
	private BigDecimal currency;

	@NumberFormat(style=Style.PERCENT)
	private BigDecimal percent;
	
	private InquiryType inquiry;
	
	private String inquiryDetails;
	private boolean check_email;
	private boolean check_phone;
	
	
	private boolean subscribeNewsletter;
	
	
	private boolean check;
    private boolean exist;
	
	
	private Map<String, String> additionalInfo;

	public FormBean(String name, String domain, String email, Date expDate,String phone,String string){//String ifemail, String ifphone){
		this.name=name;
		this.domain=domain;
		this.email=email;
		this.birthDate=expDate;
		this.phone=phone;
		this.inquiryDetails=string;
      
	}
	public FormBean(){}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public boolean Validate_map(){
		return true;
		
	}
	
	//////////////////////////
	public boolean getCheck_email() {
		return check_email;
	}

	public void setCheck_email(boolean check) {
		this.check_email = check;
	}
	
	public boolean getCheck_phone() {
		return check_email;
	}

	public void setCheck_phone(boolean check) {
		this.check_phone = check;
	}
	///////////////////////

	public void setCheck(boolean check) {
		this.check = check;
	}
	public boolean getCheck() {
		return check;
	}
	public boolean getExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getCurrency() {
		return currency;
	}

	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public InquiryType getInquiry() {
		return inquiry;
	}

	public void setInquiry(InquiryType inquiry) {
		this.inquiry = inquiry;
	}

	public String getInquiryDetails() {
		return inquiryDetails;
	}

	public void setInquiryDetails(String inquiryDetails) {
		this.inquiryDetails = inquiryDetails;
	}

	public boolean isSubscribeNewsletter() {
		return subscribeNewsletter;
	}

	public void setSubscribeNewsletter(boolean subscribeNewsletter) {
		this.subscribeNewsletter = subscribeNewsletter;
	}

	public Map<String, String> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Map<String, String> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("properties name=");
        if (name != null) {
        	sb.append("'").append(name).append("', ");
        } else {
        	sb.append(name).append(", ");
        }
        
        sb.append("properties domain=");
        if (domain != null) {
        	sb.append("'").append(domain).append("', ");
        } else {
        	sb.append(domain).append(", ");
        }
        sb.append("properties email=");
        if (email != null) {
        	sb.append("'").append(email).append("', ");
        } else {
        	sb.append(email).append(", ");
        }
        sb.append("id=").append(id).append(", ");
        sb.append("check_email=").append(check_email).append(", ");
        sb.append("check_phone=").append(check_phone).append(", ");
        sb.append("age=").append(age).append(", ");
        sb.append("birthDate=").append(birthDate).append(", ");
        sb.append("phone=");
        if (phone != null) {
        	sb.append("'").append(phone).append("', ");
        } else {
        	sb.append(phone).append(", ");
        }
        sb.append("currency=").append(currency).append(", ");
        sb.append("percent=").append(percent).append(", ");
        sb.append("inquiry=").append(inquiry).append(", ");
        sb.append("inquiryDetails=");
        if (inquiryDetails != null) {
        	sb.append("'").append(inquiryDetails).append("', ");
        } else {
        	sb.append(inquiryDetails).append(", ");
        }
        sb.append("subscribeNewsletter=").append(subscribeNewsletter).append(", ");
        sb.append("additionalInfo=").append(additionalInfo);
        return sb.toString();
    }
}