package db.integration;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

public interface CustomerDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Customer table.
	 */
	public void create(Integer id,String name, String domain,String email, String comment,String phone,String date,String notify1,String notify2,Date todate,int notifications);
    
	public int get_last_id();
	/**
	 * This is the method to be used to list down a record from the Customer
	 * table corresponding to a passed Customer id.
	 */
	public Customer getCustomer(String id);
	public int updateData(int parseInt, String fieldValue, String fieldValue2,
			String fieldValue3, String fieldValue4, String fieldValue5,
			String fieldValue6, String notify1, String notify2) ;
	
	public List<Customer> convert_Date(List<Customer> customerList) throws ParseException;
	/**
	 * This is the method to be used to list down all the records from the
	 * Customer table.
	 * @throws ParseException 
	 */
	
	public List<Customer> listCustomers() throws ParseException;

	/**
	 * This is the method to be used to delete a record from the Customer table
	 * corresponding to a passed Customer id.
	 * @return 
	 */
	public Customer delete(Integer id);

	/**
	 * This is the method to be used to update a record into the Customer table.
	 */
	public void update(Integer id, String domain);
	/**
	 * This is the method to be used to update comments in a record into the Customer table.
	 */
	public void update_comments(Integer id, String comment);
	
	public boolean find_existing_domain(String domain,int id);
	public Customer delete_and_move(int given_id) ;
	public List<Customer> sort_name_listCustomers() ;
	public List<Customer> sort_domain_listCustomers();
	public List<Customer> sort_email_listCustomers();
	public List<Customer> sort_charge_listCustomers();
	public List<Customer> sort_phone_listCustomers();
	public List<Customer> sort_date_listCustomers();
	public List<Customer> sort_notifications_listCustomers(List<Customer> cus);
	public List<Customer> search_database_with_key(String word, String category);
	
	public List<Customer> sort_name_search_listCustomers(List<Customer> customerList);
	public List<Customer> sort_email_search_listCustomers(List<Customer> customerList);
	public List<Customer> sort_domain_search_listCustomers(List<Customer> customerList);
	public List<Customer> sort_phone_search_listCustomers(List<Customer> customerList);
	public List<Customer> sort_date_search_listCustomers(List<Customer> customerList);
	public List<Customer> sort_charge_search_listCustomers(List<Customer> customerList);
	public List<Customer> sort_id_search_listCustomers( List<Customer> customerList);
	
	public List<Customer> check_exp_Dates();
	public void add_one_more_notification(String note);
	
	public List<Customer> charge_deptors(List<Customer> cus) ;
	public void payment(int given_id,int result);
	public void check_increase_charge();
}