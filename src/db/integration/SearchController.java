
package db.integration;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;





@Controller
@RequestMapping("/search")
@SessionAttributes("searchBean")

public class SearchController {

		//CustomerJDBCTemplate customer;  
		// Invoked on every request

	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	 CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context.getBean("CustomerJDBCTemplate");
	 List<Customer> customerList=null;
	
	
		@ModelAttribute
		public void ajaxAttribute(WebRequest request, Model model,@RequestParam String sort) {
			model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
			model.addAttribute("empty_list","");
			if (customerList!=null){
				model.addAttribute("empty_list","no");
				customerList=check_sort_value(sort,customerList);
				if (customerList.isEmpty()==true){
					model.addAttribute("empty_list","");
				}
			}
			
			model.addAttribute("customerList",customerList);
			
		}

		
		@ModelAttribute("searchBean")
		public SearchBean getCustomer() {
			
			  return new SearchBean();
			  
			
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public void form() {
		}
		
		
		@RequestMapping(method=RequestMethod.POST)
		
		public String processSubmit(@Valid SearchBean searchBean, BindingResult result, 
									@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
									Model model, RedirectAttributes redirectAttrs,@RequestParam String sort,SessionStatus status) {
			
			
			String word=(String) result.getFieldValue("word");
			String category=(String) result.getFieldValue("category");
			
			
			if (result.hasErrors()) {
				return null;
			}
			
			String message = "Form submitted successfully.  Bound " + searchBean;
			// Success response handling
			if (ajaxRequest) {

				model.addAttribute("message", message);
				return null;
			} else {
				//JOptionPane.showMessageDialog(null, "Edit");

				
				customerList=myJDBCTemplate.search_database_with_key(word,category);
				customerList=check_sort_value(sort,customerList);
				
				
				
				redirectAttrs.addFlashAttribute("message", message);
				redirectAttrs.addFlashAttribute("customerList", customerList);
				
				if (category.equalsIgnoreCase("all")){
					category="id";
				}
				
				redirectAttrs.addFlashAttribute("category", category);
				redirectAttrs.addFlashAttribute("word", word);
				redirectAttrs.addFlashAttribute("empty_list", "no");
				
				if (customerList.isEmpty()==true){
					//JOptionPane.showMessageDialog(null, "Edit");
					//redirectAttrs.addFlashAttribute("word", "");
					redirectAttrs.addFlashAttribute("empty_list", "");
				}
				
				
				model.addAttribute("customerList",customerList);
				ModelAndView mv=new ModelAndView("customerList", "customerList", customerList);
				searchBean.setCustomerList(customerList);
				
				return "redirect:/search?sort="+category;			
			}
			
			
			
		}


		private List<Customer> check_sort_value(String sort, List<Customer> customerList) {
			if (sort.equalsIgnoreCase("name")){
				  customerList = myJDBCTemplate.sort_name_search_listCustomers(customerList);  
			 }
			 else if (sort.equalsIgnoreCase("email")){
				 customerList = myJDBCTemplate.sort_email_search_listCustomers(customerList);  
			 }
			 else if (sort.equalsIgnoreCase("phone")){
				 customerList = myJDBCTemplate.sort_phone_search_listCustomers(customerList);  
			 }
			 else if (sort.equalsIgnoreCase("domain")){
				 customerList = myJDBCTemplate.sort_domain_search_listCustomers(customerList);  
			 }
			 else if (sort.equalsIgnoreCase("charge")){
				 customerList = myJDBCTemplate.sort_charge_search_listCustomers(customerList);  
			 }
			 else if (sort.equalsIgnoreCase("date")){
				 customerList = myJDBCTemplate.sort_date_search_listCustomers(customerList);  
			 }
			 else{
				 customerList = myJDBCTemplate.sort_id_search_listCustomers(customerList);  
			 }
			return customerList;
		}

		
	}

