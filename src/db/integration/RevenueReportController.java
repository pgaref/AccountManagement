package db.integration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
@Controller
public class RevenueReportController extends AbstractController{

	@Override
	@RequestMapping(value="/RevenueSummary")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String output =
			ServletRequestUtils.getStringParameter(request, "output");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		PdfRevenueReportView bean = (PdfRevenueReportView) context.getBean("PdfRevenueReportView");
		 CustomerJDBCTemplate myJDBCTemplate = (CustomerJDBCTemplate) context.getBean("CustomerJDBCTemplate");
		 
		//dummy data
		List<Customer> revenueData = new ArrayList<Customer>();
		revenueData= myJDBCTemplate.listCustomers();
		
		
		if(output ==null || "".equals(output)){
			//return normal view
			return new ModelAndView("RevenueSummary","revenueData",revenueData);
			
		}else if("PDF".equals(output.toUpperCase())){
			//return excel view
			return new ModelAndView(bean,"revenueData",revenueData);
			
		}else{
			//return normal view
			return new ModelAndView("RevenueSummary","revenueData",revenueData);
			
		}
		
	}
	
}