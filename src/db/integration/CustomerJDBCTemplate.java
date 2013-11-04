package db.integration;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerJDBCTemplate implements CustomerDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id, String domain, String comment) {
		String SQL = "insert into customer (ID, domain, comments) values (?, ?, ?)";

		jdbcTemplateObject.update(SQL, id, domain, comment);
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

}