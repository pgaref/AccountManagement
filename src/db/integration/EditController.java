
package db.integration;

import java.io.IOException;
import java.lang.annotation.Annotation;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;





@Controller
@RequestMapping("/edit")
@SessionAttributes("formBeam")
public class EditController {

		//CustomerJDBCTemplate customer;  
		// Invoked on every request

	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	 CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context.getBean("CustomerJDBCTemplate");
	 
	
	
		@ModelAttribute
		public void ajaxAttribute(WebRequest request, Model model) {
			model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
		}

		
		@ModelAttribute("formBean")
		public FormBean getCustomer(@RequestParam String id) {
			
			 Customer customer = myJDBCTemplate.getCustomer(id);  
				  
			  Map<String, Object> map_info = new HashMap<String, Object>();  
			  map_info.put("customer", customer);
			  boolean email=false,phone=false;
			  String ifemail="false",ifphone="false";
			  if (myJDBCTemplate.get_Notify(id).equalsIgnoreCase("email,phone")){
				  email=true;
				  phone=true;
				  ifemail="true";
				  ifphone="true";
			  }
			  else if (myJDBCTemplate.get_Notify(id).equalsIgnoreCase("email")){
				  email=true;
				  phone=false;
				  ifemail="true";
				  ifphone="false";
			  }
			  else if (myJDBCTemplate.get_Notify(id).equalsIgnoreCase("phone")){
				  email=false;
				  phone=true;
				  ifemail="false";
				  ifphone="true";
			  }
			  Map<String, String> addition=new HashMap<String , String >();
			  addition.put("email", ifemail);
			  addition.put("phone",ifphone);
			  
			  FormBean bean= new FormBean(customer.getName(),customer.getDomain(),customer.getEmail(),customer.getExpDate(),customer.getMobile(),customer.getComments());//,ifemail,ifphone);
			 
			
			  map_info.put("email",email);
			  map_info.put("phone", phone);
			  
			  ModelAndView model= new ModelAndView("edit", "map", map_info);
			  
			  return bean;
			
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public void form() {
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public String processSubmit(@Valid FormBean formbean, BindingResult result, 
									@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
									Model model, RedirectAttributes redirectAttrs) {
			
			String notify1= "false";
			String notify2="false";
			String email=(String)result.getFieldValue("email");
			String phone=(String)result.getFieldValue("phone");
			
			if (!email.equalsIgnoreCase("")){
				notify1="true";
			}
			if (!phone.equalsIgnoreCase("")){
				notify2="true";
			}

			/////////////////////////////////////////////////////////////////////
			if ( notify1.equals("false")){
												// an den exei epileksei kanena apo ta checkboxes
				if (notify2.equals("false")){
					result.rejectValue("check", "error.user", "  You must choose at least one ");

				}
			}
			/////////////////////////////////////////////////////////////////////
			if (!notify1.equals("false")){ // an exei epileksei to email
				if (notify2.equals("false")){ // kai den exei epileksei to phone
					
					formbean.setPhone(null);
				}
				
			}
			if (notify1.equals("false")){ // an den exei epileksei to email
				if (!notify2.equals("false")){ // kai  exei epileksei to phone
					formbean.setEmail(null);
				}
				
			}
			/////////////////////////////////////////////////////////////////////
			if (!notify1.equals("false")){  //an exei epileksei to email
				if ( result.getFieldValue("email").equals("")){ //kai den to exei sumplirwsei
					result.rejectValue("check", "error.user", "  You must fill the email");
				}
				
				
			}
			if (!notify2.equals("false")){  //an exei epileksei to phone
				if ( result.getFieldValue("phone").equals("")){  //kai den to exei sumplirwsei
					result.rejectValue("check", "error.user", "  You must fill the phone");
				}
				
			}
			if (!notify1.equals("false")){ //an exei epilesei to email 
				String EMAIL_PATTERN = 
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";						//kane check an einai valid
				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher  matcher = pattern.matcher(((String)result.getFieldValue("email")));
				
				if (matcher.matches()==false){
					result.rejectValue("email", "error.user", " Not a well-formed email address ");
				}
				
			}
			if (!notify2.equals("false")){
				Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{7}");
			      Matcher matcher = pattern.matcher(((String)result.getFieldValue("phone")));
			 
			      if (!matcher.matches()) {
			    	  result.rejectValue("phone", "error.user", " Not a well-formed phone number ");
			      }
			      
			     
			}
			boolean exist=myJDBCTemplate.find_existing_domain((String) result.getFieldValue("domain"),Integer.parseInt((String)result.getFieldValue("id")));
			if (exist ==true){
				result.rejectValue("exist", "error.user", "This Domain already used by different user");
			}
			
			if (result.hasErrors()) {
				
				return null;
			}
			int done=myJDBCTemplate.updateData(Integer.parseInt((String)result.getFieldValue("id")),(String)result.getFieldValue("name"),(String)result.getFieldValue("domain"),(String) result.getFieldValue("email"),(String)result.getFieldValue("inquiryDetails"),(String)result.getFieldValue("phone"),(String)result.getFieldValue("birthDate"),notify1,notify2);
			if (done!=1){
				result.rejectValue("exist", "error.user", "Data can not be renewed");
			}
			if (result.hasErrors()) {
				return null;
			}
			// Typically you would save to a db and clear the "form" attribute from the session 
			// via SessionStatus.setCompleted(). For the demo we leave it in the session.
			String message = "Form submitted successfully.  Bound " + formbean;
			// Success response handling
			if (ajaxRequest) {
				// prepare model for rendering success message in this request
				model.addAttribute("message", message);
				return null;
			} else {
				// store a success message for rendering on the next request after redirect
				// redirect back to the form to render the success message along with newly bound values
				redirectAttrs.addFlashAttribute("message", message);
				return "redirect:/customerList?sort=id";			
			}
			
			
			
		}

		
	}
