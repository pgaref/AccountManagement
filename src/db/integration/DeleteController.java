
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
@RequestMapping("/delete")
@SessionAttributes("history")
public class DeleteController {

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

		
		@ModelAttribute("history")
		public History getCustomer(@RequestParam String id) {
			
			 Customer customer = myJDBCTemplate.getCustomer(id);  
			  History history= new History(customer.getID(),customer.getName(),customer.getDomain(),new Date(),"",customer.getEmail(),customer.getMobile());
			ModelAndView model= new ModelAndView("history", "history", customer);
			  
			  return history;
			
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public void form() {
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public String processSubmit(@Valid History history, BindingResult result, 
									@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
									Model model, RedirectAttributes redirectAttrs) {
			
			
			if (result.hasErrors()) {
				return null;
			}
			
			String message = "Form submitted successfully.  Bound " + history;
			// Success response handling
			if (ajaxRequest) {
				// prepare model for rendering success message in this request
				model.addAttribute("message", message);
				return null;
			} else {
				// store a success message for rendering on the next request after redirect
				// redirect back to the form to render the success message along with newly bound values
				redirectAttrs.addFlashAttribute("message", message);
				 Customer cus=null;
				 int last_id=myJDBCTemplate.get_last_id();
				 int given_id=Integer.parseInt((String)result.getFieldValue("id"));
				 if (given_id<last_id){
					 cus=myJDBCTemplate.delete_and_move(given_id);
				 }
				 else{
					 cus=myJDBCTemplate.delete(given_id);
				 }
				 Date date= new Date();
				 int last_id_history=myJDBCTemplate_history.get_last_id();
				 myJDBCTemplate_history.create(last_id_history,cus.getName(),cus.getDomain(), date,(String)result.getFieldValue("delComments"), cus.getEmail(),cus.getMobile());
				 return "redirect:/customerList?sort=id";

					
			}
			
			
			
		}

		
	}
