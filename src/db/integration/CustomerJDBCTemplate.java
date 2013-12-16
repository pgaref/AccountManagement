package db.integration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class CustomerJDBCTemplate implements CustomerDAO {

	   
	 private DataSource dataSource;  
	private JdbcTemplate jdbcTemplateObject;
	private DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	public void create(Integer id,String name, String domain,String email, String comment,String phone,String expiration_date,String notify1,String notify2,Date registration_date,int notifications) {
		if (email==null){
			email="";
		}
		if (phone==null){
			phone="";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = expiration_date;
	    Date date1=null;
		try {
	 
			 date1 = formatter.parse(dateInString);
			
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String notify="";
		
		
		if ((notify1.equalsIgnoreCase("true")) && (notify2.equalsIgnoreCase("true"))){
			notify="email,phone";
		}
		else if (notify1.equalsIgnoreCase("true")){
			 notify="email";
		}
		else if (notify2.equalsIgnoreCase("true")){
			notify="phone";
		}
		int month=registration_date.getMonth();
		
		
		String SQL = "insert into customer (ID,name, domain,email, comments,mobile,expiration_date,notify,registration_date,notifications,month) values (?, ?, ?, ?, ? ,?, ?, ? ,?,?,?)";

		jdbcTemplateObject.update(SQL, id,name, domain,email, comment,phone,date1,notify,registration_date,0,month);
		return;
	}

	@Override
	public Customer getCustomer(String id) {
		List<Customer> customerList = new ArrayList<Customer>();  
		String SQL = "select * from customer where ID = ?";
		customerList.add(  jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new CustomerMapper()));

		return customerList.get(0);
	}
	
	public int updateData(int id, String name,String domain,String email,String comments,String phone, String expiration_date,String notify1,String notify2 ) {  
		if (email==null){
			email="";
		}
		if (phone==null){
			phone="";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = expiration_date;
	    Date date1=null;
		try {
	 
			 date1 = formatter.parse(dateInString);
			
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String notify="";
		
		
		if ((notify1.equalsIgnoreCase("true")) && (notify2.equalsIgnoreCase("true"))){
			notify="email,phone";
		}
		else if (notify1.equalsIgnoreCase("true")){
			 notify="email";
		}
		else if (notify2.equalsIgnoreCase("true")){
			notify="phone";
		}
		if (comments==null){
			comments="";
		}
		
		String SQL = "update customer set  name= ?,domain= ?,email= ?,comments= ?,mobile= ?,expiration_date= ? where ID = ? ";
		return  jdbcTemplateObject.update(SQL,new Object[] {  name,  
				domain, email, comments,phone, date1 ,id });
		
		
		
		
		  
	}  
	public List<Customer> convert_Date(List<Customer> customerList){

		ListIterator<Customer> it2 = customerList.listIterator();  
        while(it2.hasNext()){
            Customer p = it2.next();
           
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String output = outputFormatter.format(p.getExpDate()); 
            p.setExpDate_input(output);
            
            
        } 
		return customerList;
	}
	
	@Override
	public List<Customer> listCustomers()   {
		List<Customer> customerList = new ArrayList<Customer>(); 
		String SQL = "select * from customer order by ID";
		
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	
	public String get_Notify(String id){
		
	
		String SQL = "select * from customer where ID = ?";
		List<Customer> customerList = new ArrayList();
		customerList.add(  jdbcTemplateObject.queryForObject(SQL,
				new Object[] { id }, new CustomerMapper()));
		
		
		String notify="";
		if (!customerList.get(0).getEmail().equalsIgnoreCase("nomail")){
		    if (!customerList.get(0).getMobile().equalsIgnoreCase("nomobile")){
				notify="email,mobile";
		    }
		}
	    if (customerList.get(0).getEmail().equalsIgnoreCase("nomail")){
		    if (!customerList.get(0).getMobile().equalsIgnoreCase("nomobile")){
				notify="mobile";
		    }
		}
	    if (!customerList.get(0).getEmail().equalsIgnoreCase("nomail")){
		    if (customerList.get(0).getMobile().equalsIgnoreCase("nomobile")){
				notify="email";
		    }
			
		}
		return notify;
		
	}

	public Customer delete(Integer id) {
		
		String SQL_ret = "select * from customer where ID = ?";
		List<Customer> customerList = new ArrayList();
		customerList.add(  jdbcTemplateObject.queryForObject(SQL_ret,
				new Object[] { id }, new CustomerMapper()));
		
		
		String SQL = "delete from customer where ID = ?";
		jdbcTemplateObject.update(SQL, id);
		return  customerList.get(0);
	}

	public void update(Integer id, String domain) {
		String SQL = "update customer set domain = ? where ID = ?";
		jdbcTemplateObject.update(SQL, domain, id);
		return;
	}
	
	public void update_comments(Integer id, String comment) {
		String SQL = "update customer set comments = ? where ID = ?";
		jdbcTemplateObject.update(SQL, comment, id);
		return;
	}

	public Customer search_domain(String  domain) throws ParseException {
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

	public Customer search_email(String email)   {
		
		
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
	public int get_last_id(){
		String SQL = "select * from customer ";
		jdbcTemplateObject.execute(SQL);
		List<Customer> cus = jdbcTemplateObject
				.query(SQL, new CustomerMapper());
		

		return cus.size();
	}
	public Customer search_name(String name ) throws ParseException {
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

	public boolean find_existing_domain(String domain, int last_id) {
		String SQL = "select * from customer";
		List<Customer> cus = jdbcTemplateObject
				.query(SQL, new CustomerMapper());

		ListIterator<Customer> it2 = cus.listIterator();  
        while(it2.hasNext()){
            Customer p = it2.next();
            
            if (p.getDomain().equalsIgnoreCase(domain)){
    			if (p.getID()!=last_id){
    				return true;
    			}
            	
            }
            
        }  
		
		
		return false;
	}
	public Customer delete_and_move(int given_id) {

		String SQL_ret = "select * from customer where ID = ?";
		List<Customer> customerList = new ArrayList();
		customerList.add(  jdbcTemplateObject.queryForObject(SQL_ret,
				new Object[] { given_id }, new CustomerMapper()));
		
		
		String SQL = "delete from customer where ID = ?";
		jdbcTemplateObject.update(SQL,given_id);
		
		String SQL1= "update customer SET ID = ID - 1  where ID > ?";
		
		jdbcTemplateObject.update(SQL1, given_id);
		
		return customerList.get(0);
		
		
		
	}
	public List<Customer> sort_name_listCustomers() {
		List customerList = new ArrayList(); 
		String SQL = "select * from customer order by name";
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_domain_listCustomers() {
		List customerList = new ArrayList(); 
		String SQL = "select * from customer order by domain";
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_email_listCustomers() {
		List customerList = new ArrayList(); 
		String SQL = "select * from customer order by email";
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_charge_listCustomers() {
		List customerList = new ArrayList(); 
		String SQL = "select * from customer order by charge";
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_phone_listCustomers() {
		List customerList = new ArrayList(); 
		String SQL = "select * from customer order by mobile";
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_date_listCustomers() {
		List customerList = new ArrayList(); 
		String SQL = "select * from customer order by expiration_date";
		
		customerList = jdbcTemplateObject.query(SQL, new CustomerMapper());
		customerList=convert_Date(customerList);
		return customerList;
	}
	
	public List<Customer> sort_notifications_listCustomers(List<Customer> cus) {
		Collections.sort(cus, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getHow_long()-(another.getHow_long());
            }
        });
       return cus;
	}
	
	public List<Customer> search_database_with_key(String word, String category) {
		
		List<Customer> customerList = new ArrayList(); 
		
		String SQL="";
		String finalName= word.toLowerCase().trim();
		if (category.equalsIgnoreCase("all")){
			SQL = "SELECT * FROM customer WHERE name LIKE '%"+finalName+"%' or email  LIKE '%"+finalName+"%'  or domain  LIKE '%"+finalName+"%'  or mobile  LIKE '%"+finalName+"%'";
		}
		else if (category.equalsIgnoreCase("name")){
			SQL = "SELECT * FROM customer WHERE name LIKE '%"+finalName+"%' ";
		}
		else if (category.equalsIgnoreCase("domain")){
			SQL = "SELECT * FROM customer WHERE domain LIKE '%"+finalName+"%' ";
		}
		else if (category.equalsIgnoreCase("email")){
			SQL = "SELECT * FROM customer WHERE email LIKE '%"+finalName+"%' ";
		}
		else if (category.equalsIgnoreCase("phone")){
			SQL = "SELECT * FROM customer WHERE mobile LIKE '%"+finalName+"%' ";
		}
		
		try {
			 customerList=jdbcTemplateObject.query(SQL, new CustomerMapper());
			
		 } catch(EmptyResultDataAccessException dataAccessException) {
	       
	         return customerList;
		 }	
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_name_search_listCustomers(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getName().compareToIgnoreCase(another.getName());
            }
        });
		
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_email_search_listCustomers(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getEmail().compareToIgnoreCase(another.getEmail());
            }
        });
		
		customerList=convert_Date(customerList);
		return customerList;
		
	}
	public List<Customer> sort_phone_search_listCustomers(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getMobile().compareToIgnoreCase(another.getMobile());
            }
        });
		customerList=convert_Date(customerList);
		
		return customerList;
	}
	public List<Customer> sort_domain_search_listCustomers(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getDomain().compareToIgnoreCase(another.getDomain());
            }
        });
		
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_charge_search_listCustomers(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getCharge()-(another.getCharge());
            }
        });
		
		
		return customerList;
	}
	public List<Customer> sort_date_search_listCustomers(List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getExpDate().compareTo(another.getExpDate());
            }
        });
		
		customerList=convert_Date(customerList);
		return customerList;
	}
	public List<Customer> sort_id_search_listCustomers( List<Customer> customerList) {
		Collections.sort(customerList, new Comparator<Customer>() {
            public int compare(Customer contact, Customer another) {
            	
                return contact.getID()-(another.getID());
            }
        });
		customerList=convert_Date(customerList);
		return customerList;
	}
	
	public List<Customer> check_exp_Dates() {
		List<Customer> cus = listCustomers();
		for (int i = 0; i < cus.size(); i++) {
			
			Customer p=cus.get(i);
			DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String output = outputFormatter.format(p.getExpDate()); 
            Date cus_date=p.getExpDate();
            p.setExpDate_input(output);
            
            
           
            Date d= new Date();
            Date today=new Date();;
			try {
				today = outputFormatter.parse(outputFormatter.format(d));
			} catch (ParseException e) {
				e.printStackTrace();
			}
            String output2 = outputFormatter.format(today); 
   
            if (cus_date.compareTo(today) >= 0) {
            	int diffInDays = (int)( (cus_date.getTime() - today.getTime()) 
                        / (1000 * 60 * 60 * 24) );
            	p.setHow_long(diffInDays);
            	
            }
            
		}
		int len=cus.size();
		for (int i = 0; i <len; i++) {
			
			Customer p=cus.get(i);
			if (p.getHow_long()>30){
				cus.remove(p);
			}
		}
		
		
      
        return cus;
		
	}
	public void add_one_more_notification(String note) {
		
		
		List<Customer> customerList = new ArrayList<Customer>();  
		String SQL1 = "select * from customer where ID = ?";
		customerList.add(  jdbcTemplateObject.queryForObject(SQL1,
				new Object[] { Integer.parseInt(note) }, new CustomerMapper()));
		  
		String SQL = "update customer set notifications = ? where ID = ?";
		int new_note=customerList.get(0).getNotifications() +1;
		jdbcTemplateObject.update(SQL,new_note, Integer.parseInt(note));

		
	}
	public List<Customer> charge_deptors( List<Customer> cus) {
		List<Customer> debtors=new ArrayList<Customer>();
		for (int i = 0; i < cus.size(); i++) {
			
			Customer p=cus.get(i);
			
			if (p.getCharge()>0){
				debtors.add(p);
			}
			
		}
		return debtors;

	}
	public void payment(int given_id,int result) {
		List<Customer> customerList = new ArrayList<Customer>();  
		String SQL1 = "select * from customer where ID = ?";
		customerList.add(  jdbcTemplateObject.queryForObject(SQL1,
				new Object[] { given_id }, new CustomerMapper()));
		
		Customer change=customerList.get(0);
		change.setCharge(change.getCharge()- result);
		
	
		String SQL = "update customer set charge = ? where ID = ?";
		jdbcTemplateObject.update(SQL, change.getCharge(), given_id);
		
		
		  
		
	}
	public void check_increase_charge() {
		List<Customer> customers= new ArrayList<Customer>();
		List<Customer> customer_charge= new ArrayList<Customer>();
		List<Date> customerList_exp= new ArrayList<Date>();
		List<Date> customerList_reg= new ArrayList<Date>();
		List<Integer> month= new ArrayList<Integer>();
		
		
	
		
	     customers=listCustomers();
	     for (int i = 0; i < customers.size(); i++) {
	    	 
	    	 Customer p =customers.get(i);
	    	
	    	 customerList_reg.add( p.getRegDate());
	    	 customerList_exp.add(p.getExpDate());
	    	 month.add(p.getMonth());
	     }
	
		System.out.println("customerList_reg       "+ customerList_reg.size());
		for (int i = 0; i < customerList_reg.size(); i++) {
			
			

			Date reg_i= customerList_reg.get(i);
			Date today=new Date();
			int month_i=month.get(i);
			int diffInDays = (int)( (today.getTime() - reg_i.getTime())  / (1000 * 60 * 60 * 24) );
			/*System.out.println("diafora meres=======================   "+diffInDays);
			System.out.println("minas pou enimerw8ikan " +month_i );
			System.out.println("minas pou exoume " +(today.getMonth() +1));
			
			System.out.println("imera pou graftikan " +reg_i.getDate() );
			System.out.println("imera pou exoume " +today.getDate() );
			*/
			if(diffInDays >=0){
				
				if(month_i==today.getMonth()+1 - 1){
					
					if (reg_i.getDate()==today.getDate()){
					
					
					String SQL1 = "select * from customer where ID = ?";
					customer_charge.add(  jdbcTemplateObject.queryForObject(SQL1,
							new Object[] { customers.get(i).getID() }, new CustomerMapper()));
					
					int charge=customer_charge.get(0).getCharge();
					int id=customer_charge.get(0).getID();
					
					String SQL = "update customer set charge = ? where ID = ?";
					jdbcTemplateObject.update(SQL,charge+100 , id);
					
					if (month_i==12){
						month_i=0;	
					}
					
					String SQL4 = "update customer set month = ? where ID = ?";
					jdbcTemplateObject.update(SQL4,month_i+1 , id);
					}
				}
			
			
			}
		}
		

	}
	



}