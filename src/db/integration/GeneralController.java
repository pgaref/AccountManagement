package db.integration;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.validation.Valid;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.samples.mvc.form.FormBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GeneralController extends MultiActionController {

	
	 
	 ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	 CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context.getBean("CustomerJDBCTemplate");
	 HistoryJDBCTemplate myJDBCTemplate_history = (HistoryJDBCTemplate) context.getBean("HistoryJDBCTemplate");
	
	 @RequestMapping(value="/edit" )
	 public ModelAndView editUser(@RequestParam String id,  
			   @ModelAttribute Customer customer) {  
		 //JOptionPane.showMessageDialog(null, "Edit");
		 
		 customer = myJDBCTemplate.getCustomer(id);  
			  
		  Map<String, Object> map_info = new HashMap<String, Object>();  
		  map_info.put("customer", customer);
		 
		  return new ModelAndView("edit", "map", map_info);  
	 }
	 
	
	 @PostConstruct
	 public void homeUser() { 
		
		 myJDBCTemplate.check_increase_charge();
		
	 }
	

	 @RequestMapping(value="/debtors")
     public ModelAndView Deptors_User(@RequestParam String sort){
		 
		 List<Customer> debtors=null;
		 if (sort.equalsIgnoreCase("name")){
			 debtors = myJDBCTemplate.sort_name_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("email")){
			 debtors = myJDBCTemplate.sort_email_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("phone")){
			 debtors = myJDBCTemplate.sort_phone_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("domain")){
			 debtors = myJDBCTemplate.sort_domain_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("charge")){
			 debtors = myJDBCTemplate.sort_charge_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("date")){
			 debtors = myJDBCTemplate.sort_date_listCustomers();  
		 }
		 else{
			 debtors = myJDBCTemplate.listCustomers(); 
		 }
		 debtors=myJDBCTemplate.charge_deptors(debtors);
		 if (debtors==null){
			 debtors=new ArrayList<Customer>();
		 }
		 return new ModelAndView("debtors", "debtors", debtors);  
		
	 }
	 
	 @RequestMapping(value="/history")
     public ModelAndView historyUser(@RequestParam String sort){
		 
		 
		  List<History> history=null;
			 if (sort.equalsIgnoreCase("name")){
				 history = myJDBCTemplate_history.sort_name_listCustomers();  
			 }
			 else if (sort.equalsIgnoreCase("email")){
				 history = myJDBCTemplate_history.sort_email_listCustomers();  
			 }
			 else if (sort.equalsIgnoreCase("phone")){
				 history = myJDBCTemplate_history.sort_phone_listCustomers();  
			 }
			 else if (sort.equalsIgnoreCase("domain")){
				 history = myJDBCTemplate_history.sort_domain_listCustomers();  
			 }
			 else if (sort.equalsIgnoreCase("date")){
				 history = myJDBCTemplate_history.sort_date_listCustomers();  
			 }
			 else if  (sort.equalsIgnoreCase("all")){
				 history=myJDBCTemplate_history.clear_history();
			 }
			 else{
				 history = myJDBCTemplate_history.listCustomers();  
			 }
			 if (history==null){
				 history=new ArrayList<History>();
			 }
			 return new ModelAndView("history", "history", history);  
		
	 }
	 
	 @RequestMapping(value="/customerList")
     public ModelAndView showUsers(@RequestParam String sort) throws ParseException{
		 List<Customer> customerList=null;
		 if (sort.equalsIgnoreCase("name")){
			  customerList = myJDBCTemplate.sort_name_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("email")){
			 customerList = myJDBCTemplate.sort_email_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("phone")){
			 customerList = myJDBCTemplate.sort_phone_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("domain")){
			 customerList = myJDBCTemplate.sort_domain_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("charge")){
			 customerList = myJDBCTemplate.sort_charge_listCustomers();  
		 }
		 else if (sort.equalsIgnoreCase("date")){
			 customerList = myJDBCTemplate.sort_date_listCustomers();  
		 }
		 else{
		      customerList = myJDBCTemplate.listCustomers();  
		 }
		 if (customerList==null){
			 customerList=new ArrayList<Customer>();
		 }
		 return new ModelAndView("customerList", "customerList", customerList);  
		
	 } 
	 
	 @RequestMapping(value="/notifications")
     public ModelAndView notifications(@RequestParam String note , @RequestParam String action) throws ParseException{	
		 Customer cus=null;
		 List<Customer> notifications=new ArrayList<Customer>();
		 notifications=myJDBCTemplate.check_exp_Dates();
		 if (!note.equalsIgnoreCase("all")){
			 
			  myJDBCTemplate.add_one_more_notification(note);
			  if(action.equalsIgnoreCase("delete")){
					
					 int last_id=myJDBCTemplate.get_last_id();
					 int given_id=Integer.parseInt(note);
					 if (given_id<last_id){
						 cus=myJDBCTemplate.delete_and_move(given_id);
					 }
					 else{
						 cus=myJDBCTemplate.delete(given_id);
					 }
					 notifications=myJDBCTemplate.check_exp_Dates();
					 
					
				     Date date= new Date();
				     int last_id_history=myJDBCTemplate_history.get_last_id();
					 myJDBCTemplate_history.create(last_id_history,cus.getName(),cus.getDomain(), date,"deleting_com", cus.getEmail(),cus.getMobile());

				 } 
			 
		}
		
		 notifications=myJDBCTemplate.sort_notifications_listCustomers(notifications);
		 if (notifications==null){
			 notifications=new ArrayList<Customer>();
		 }
		 ModelAndView model = new ModelAndView("notifications", "notifications", notifications);  
		 return model;
		// return "redirect:/notifications?note=all";
		
	 } 
	 
	
}
