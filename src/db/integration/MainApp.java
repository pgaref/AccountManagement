package db.integration;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MainApp {
	public static void main(String[] args) {
		
		
		/*
		 * Customer Fields!!!
		 
		private Integer ID;
		private Timestamp regDate;
		private Timestamp expDate;
		private String domain;
		private String email;
		private String mobile;
		private Integer charge;
		private String comments;
		private Timestamp lastUpdate;
		 
		*/ 
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");

		CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context
				.getBean("CustomerJDBCTemplate");

		System.out.println("------Records Creation--------");
		myJDBCTemplate.create(1,"pgaref", "comment 1");
		myJDBCTemplate.create(2,"katikar", "comment 2");
		myJDBCTemplate.create(15, "whatever", "comment 3");

		System.out.println("------Listing Multiple Records--------");
		List<Customer> customers = myJDBCTemplate.listCustomers();
		for (Customer record : customers) {
			System.out.println(record);
			
		}

		System.out.println("----Updating Record with ID = 2 -----");
		myJDBCTemplate.update(2, "newDomain");

		System.out.println("----Listing Record with ID = 2 -----");
		Customer tmp = myJDBCTemplate.getCustomer(2);
		System.out.println(tmp);
	
		System.out.println("All done");
		
		/*  
		 * Cleaning Database - Primary Keys cannot be duplicate
		 */
		
		myJDBCTemplate.delete(1);
		myJDBCTemplate.delete(2);
		myJDBCTemplate.delete(15);
		
		System.out.println("Cleaning Done!");
		
	}
}
