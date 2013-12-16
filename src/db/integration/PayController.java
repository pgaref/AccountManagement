
package db.integration;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/pay")
@SessionAttributes("customer")
public class PayController {

		//CustomerJDBCTemplate customer;  
		// Invoked on every request

	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	 CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context.getBean("CustomerJDBCTemplate");
	 HistoryJDBCTemplate myJDBCTemplate_history = (HistoryJDBCTemplate) context.getBean("HistoryJDBCTemplate");
	 
	
	
		@ModelAttribute
		public void ajaxAttribute(WebRequest request, Model model) {
			
			Customer customer = myJDBCTemplate.getCustomer(request.getParameter("id"));  
			model.addAttribute("customer",customer );
			model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
		}

		
		@ModelAttribute("customer")
		public Customer getCustomer(@RequestParam String id) {
			
	    	Customer customer = myJDBCTemplate.getCustomer(id);  
			ModelAndView model= new ModelAndView("customer", "customer", customer);
			return customer;
			
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public void form() {
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public String processSubmit(@Valid Customer customer, BindingResult result, 
									@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
									Model model, RedirectAttributes redirectAttrs) {
			
			
			String new_char=(String)result.getFieldValue("new_charge");
			int given_id=Integer.parseInt((String)result.getFieldValue("id"));
			Customer cus=myJDBCTemplate.getCustomer((String)result.getFieldValue("id"));
			
			if (cus.getCharge()< Integer.parseInt(new_char)){
				result.rejectValue("exist", "error.user", "Payment > Amount Due");
			}
			
			if (result.hasErrors()) {
				return null;
			}
			
			String message = "Form submitted successfully.  Bound " + customer;
			// Success response handling
			if (ajaxRequest) {
				// prepare model for rendering success message in this request
				model.addAttribute("message", message);
				return null;
			} else {
				// store a success message for rendering on the next request after redirect
				// redirect back to the form to render the success message along with newly bound values
				redirectAttrs.addFlashAttribute("message", message);
				
				 myJDBCTemplate.payment(given_id, Integer.parseInt(new_char)); 
				
				 return "redirect:/debtors?sort=id";

					
			}
			
			
			
		}

		
	}
