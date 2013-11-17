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
		//myJDBCTemplate.create(1,"pgaref","Dpgaref", "pgaref@csd.uoc.gr","comment 1");
		//myJDBCTemplate.create(2,"katikar","Dkatikar", "katikar@csd.uoc.gr","comment 2");
		//myJDBCTemplate.create(15,"toulios", "Dtoulios","toulios@csd.uoc.gr", "comment 3");

		System.out.println("------Listing Multiple Records--------");
		List<Customer> customers = myJDBCTemplate.listCustomers();
		for (Customer record : customers) {
			System.out.println(record);
			
		}

		System.out.println("----Updating Record with ID = 2 -----");
		myJDBCTemplate.update(1, "newDomain");

		System.out.println("----Listing Record with ID = 2 -----");
		Customer tmp = myJDBCTemplate.getCustomer(2);
		System.out.println(tmp);
	
		System.out.println("All done");
		
		/*  
		 * Cleaning Database - Primary Keys cannot be duplicate
		 */
		
		
		myJDBCTemplate.update_comments(2,"new_comment");
		//myJDBCTemplate.delete(15);
		
		HistoryJDBCTemplate myJDBCHis = (HistoryJDBCTemplate) context
				.getBean("HistoryJDBCTemplate");
		
		java.util.Date date= new java.util.Date();
		Timestamp timestamp=new Timestamp(date.getTime()); 
		
		//myJDBCHis.create(15 , "Dtoulios",timestamp,"reason1","email1");
		
		/*
		 *search in customers 
		 * */
		Customer a=myJDBCTemplate.search_domain("Dkatikar");
		Customer b=myJDBCTemplate.search_email("katikar@csd.uoc.gr");
		Customer c=myJDBCTemplate.search_name("katikar");
		
		
		
		System.out.println("Cleaning Done!");
		
	}
}
