package db.integration;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public interface HistoryDAO {

	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the History table.
	 */
	public void create(Integer id, String name,String domain,Date off_date, String comment,String email,String phone);

	/**
	 * This is the method to be used to list down a record from the History
	 * table corresponding to a passed student id.
	 */
	public History getCustomer(Integer id);

	/**
	 * This is the method to be used to list down all the records from the
	 * History table.
	 */
	public List<History> listCustomers();

	/**
	 * This is the method to be used to delete a record from the History table
	 * corresponding to a passed Customer id.
	 */
	public void delete(Integer id);

	/**
	 * This is the method to be used to update a record into the History table.
	 */
	public void update(Integer id, String domain);
	/**
	 * This is the method to be used to update comments in a record into the History table.
	 */
	public void update_comments(Integer id, String comment);
	
	public List<History> convert_Date(List<History> customerList);
	public List<History> sort_name_listCustomers();
	public List<History> sort_email_listCustomers();
	public List<History> sort_domain_listCustomers();
	public List<History> sort_phone_listCustomers();
	public List<History> sort_date_listCustomers();
	
	public int get_last_id();
	
	public List<History> clear_history() ;
	
	
}
