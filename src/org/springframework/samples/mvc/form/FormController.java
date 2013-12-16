package org.springframework.samples.mvc.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import db.integration.Customer;
import db.integration.CustomerJDBCTemplate;


@Controller
@RequestMapping("/form")
@SessionAttributes("formBean")
public class FormController {

	//CustomerJDBCTemplate customer;  
	// Invoked on every request

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)

	@ModelAttribute("formBean")
	public FormBean createFormBean() {
		
		return new FormBean();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void form() {
	}

	 
	 
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid FormBean formBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model, RedirectAttributes redirectAttrs,SessionStatus status) {
		
		
		String notify1= (String)result.getFieldValue("additionalInfo[email]");
		String notify2=(String) result.getFieldValue("additionalInfo[phone]");
		if (notify2==null){
			notify2="false";
		}
		if (notify1==null){
			notify1="false";
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
				
				formBean.setPhone(null);
			}
			
		}
		if (notify1.equals("false")){ // an den exei epileksei to email
			if (!notify2.equals("false")){ // kai  exei epileksei to phone
				formBean.setEmail(null);
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
		/////////////////////////////////////////////////////////////////////
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		

		CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context
				.getBean("CustomerJDBCTemplate");

		
		
		int last_id=myJDBCTemplate.get_last_id();
		boolean exist=myJDBCTemplate.find_existing_domain((String) result.getFieldValue("domain"),last_id);
		if (exist ==true){
			result.rejectValue("exist", "error.user", "This Domain already used by different user");
		}
		
		
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	   Date todate = new Date();
	   String dateInString = (String)result.getFieldValue("birthDate");
	    Date date1=null;
		try {
	 
			 date1 = formatter.parse(dateInString);
			
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(todate.compareTo(date1)>0){
			result.rejectValue("exist", "error.user", "Today is after the Expiration Date");
			
		}
	   
		if (result.hasErrors()) {
			
			return null;
		} 
		
		myJDBCTemplate.create(last_id,(String)result.getFieldValue("name"),(String)result.getFieldValue("domain"),(String) result.getFieldValue("email"),(String)result.getFieldValue("inquiryDetails"),(String)result.getFieldValue("phone"),(String)result.getFieldValue("birthDate"),notify1,notify2,todate,0);
		
		
		if (result.hasErrors()) {
			return null;
		}
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			status.setComplete();
			
			return "redirect:/customerList?sort=id";			
		}
		
		
		
	}

	
}