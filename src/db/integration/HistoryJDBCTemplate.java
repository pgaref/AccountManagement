package db.integration;

import java.security.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class HistoryJDBCTemplate implements HistoryDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id, String domain,java.sql.Timestamp timestamp ,String comment,String email) {
		String SQL = "insert into history (ID, domain,off_date,deleting_comments,email) values (?, ?, ?, ?,?)";

		jdbcTemplateObject.update(SQL, id, domain, timestamp ,comment,email);
		System.out.println("Created Record ID = " + id + " Domain = " + domain + "comment: "+comment);
		return;
	}

	@Override
	public History getCustomer(Integer id) {
		String SQL = "select * from history where ID = ?";
		History cus = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { id }, new HistoryMapper());
		return cus;
	}

	@Override
	public List<History> listCustomers() {
		String SQL = "select * from history";
		List<History> cus = jdbcTemplateObject
				.query(SQL, new HistoryMapper());
		return cus;
	}

	public void delete(Integer id) {
		String SQL = "delete from history where ID = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void update(Integer id, String domain) {
		String SQL = "update history set domain = ? where ID = ?";
		jdbcTemplateObject.update(SQL, domain, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}
	
	public void update_comments(Integer id, String comment) {
		String SQL = "update history set deleting_comments = ? where ID = ?";
		jdbcTemplateObject.update(SQL, comment, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}

	@Override
	public void create(Integer id, String domain, Timestamp off_date,
			String comment) {
		// TODO Auto-generated method stub
		
	}
}
