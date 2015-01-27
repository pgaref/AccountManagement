package db.integration;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class HistoryJDBCTemplate implements HistoryDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id,String name, String domain,Date date ,String comment,String email,String phone) {
		String SQL = "insert into history (ID,name, domain,off_date,deleting_comments,email,mobile) values (?, ?, ?, ?,?,?,?)";

		jdbcTemplateObject.update(SQL, id,name, domain, date ,comment,email,phone);
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
		cus=convert_Date(cus);
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

	

	public List<History> convert_Date(List<History> customerList){

		ListIterator<History> it2 = customerList.listIterator();  
        while(it2.hasNext()){
        	History p = it2.next();
           
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String output = outputFormatter.format(p.getDelDate()); 
            p.setDelDate_input(output);
            
            
        } 
		return customerList;
	}
	public List<History> sort_name_listCustomers() {
		List<History> customerList = new ArrayList<History>(); 
		String SQL = "select * from history order by name";
		
		customerList = jdbcTemplateObject.query(SQL, new HistoryMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}

	public List<History> sort_email_listCustomers() {
		List<History> customerList = new ArrayList<History>(); 
		String SQL = "select * from history order by email";
		
		customerList = jdbcTemplateObject.query(SQL, new HistoryMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<History> sort_domain_listCustomers() {
		List<History> customerList = new ArrayList<History>(); 
		String SQL = "select * from history order by domain";
		
		customerList = jdbcTemplateObject.query(SQL, new HistoryMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<History> sort_phone_listCustomers() {
		List<History> customerList = new ArrayList<History>(); 
		String SQL = "select * from history order by mobile";
		
		customerList = jdbcTemplateObject.query(SQL, new HistoryMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<History> sort_date_listCustomers() {
		List<History> customerList = new ArrayList<History>(); 
		String SQL = "select * from history order by off_date";
		
		customerList = jdbcTemplateObject.query(SQL, new HistoryMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}

	public int get_last_id() {
		String SQL = "select * from history ";
		jdbcTemplateObject.execute(SQL);
		List<History> cus = jdbcTemplateObject
				.query(SQL, new HistoryMapper());
		

		return cus.size();
	}

	public List<History> clear_history() {
		String SQL = "TRUNCATE TABLE history ";
		jdbcTemplateObject.update(SQL);
		return null;
	}

	
}
