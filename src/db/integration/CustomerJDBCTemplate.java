package db.integration;

import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerJDBCTemplate implements CustomerDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id,String name, String domain,String email, String comment) {
		String SQL = "insert into customer (ID,name, domain,email, comments) values (?, ? ,?, ?, ?)";

		jdbcTemplateObject.update(SQL, id,name, domain,email, comment);
		System.out.println("Created Record ID = " + id + " Domain = " + domain + "comment: "+comment);
		return;
	}

	@Override
	public Customer getCustomer(Integer id) {
		String SQL = "select * from customer where ID = ?";
		Customer cus = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { id }, new CustomerMapper());
		return cus;
	}

	@Override
	public List<Customer> listCustomers() {
		String SQL = "select * from customer";
		List<Customer> cus = jdbcTemplateObject
				.query(SQL, new CustomerMapper());
		return cus;
	}

	public void delete(Integer id) {
		String SQL = "delete from customer where ID = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void update(Integer id, String domain) {
		String SQL = "update customer set domain = ? where ID = ?";
		jdbcTemplateObject.update(SQL, domain, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}
	
	public void update_comments(Integer id, String comment) {
		String SQL = "update customer set comments = ? where ID = ?";
		jdbcTemplateObject.update(SQL, comment, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}

	public Customer search_domain(String  domain) {
		List<Customer> cus = listCustomers();
		
		ListIterator<Customer> it2 = cus.listIterator();  
        while(it2.hasNext()){
            Customer p = it2.next();
            if (p.getDomain().equalsIgnoreCase(domain)){
            	return p;
            	
            }
            
        }  
		
		return null;
	}

	public Customer search_email(String email) {
		
		
		List<Customer> cus = listCustomers();
		
		ListIterator<Customer> it2 = cus.listIterator();  
        while(it2.hasNext()){
            Customer p = it2.next();
            if (p.getEmail().equalsIgnoreCase(email)){
            	return p;
            	
            }
            
        }  
		
		return null;
	}

	public Customer search_name(String name ) {
		List<Customer> cus = listCustomers();
		
		ListIterator<Customer> it2 = cus.listIterator();  
        while(it2.hasNext()){
            Customer p = it2.next();
            if (p.getName().equalsIgnoreCase(name)){
            	return p;
            	
            }
            
        }  
		
		return null;
	}

}