package db.integration;

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
	public void create(Integer id, String domain, String comment);

	/**
	 * This is the method to be used to list down a record from the Customer
	 * table corresponding to a passed student id.
	 */
	public Customer getCustomer(Integer id);

	/**
	 * This is the method to be used to list down all the records from the
	 * Customer table.
	 */
	public List<Customer> listCustomers();

	/**
	 * This is the method to be used to delete a record from the Customer table
	 * corresponding to a passed student id.
	 */
	public void delete(Integer id);

	/**
	 * This is the method to be used to update a record into the Customer table.
	 */
	public void update(Integer id, String domain);
}