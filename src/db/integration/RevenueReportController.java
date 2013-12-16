package db.integration;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
 @Controller
public class RevenueReportController extends AbstractController{
 
	@Override
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		 System.out.println("Revue  ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
		String output =
			ServletRequestUtils.getStringParameter(request, "output");
 System.out.println("Revue  ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
		//dummy data

		Map<String,String> revenueData = new HashMap<String,String>();
		revenueData.put("1/20/2010", "$100,000");
		revenueData.put("1/21/2010", "$200,000");
		revenueData.put("1/22/2010", "$300,000");
		revenueData.put("1/23/2010", "$400,000");
		revenueData.put("1/24/2010", "$500,000");
		PdfRevenueReportView pdf=new PdfRevenueReportView();
		 Document document = new Document();
		  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("2.pdf"));
		  document.open();
		 // document.newPage();
		pdf.buildPdfDocument(revenueData, document, writer, request, response);
		// return new ModelAndView("back-up","revenueData",revenueData);
		
		if(output ==null || "".equals(output)){
		    //return normal view
		    return new ModelAndView("back-up","revenueData",revenueData);
 
		}else if("PDF".equals(output.toUpperCase())){
		    //return excel view
		    return new ModelAndView("back-up","revenueData",revenueData);
 
		}else{
		    //return normal view
		    return new ModelAndView("back-up","revenueData",revenueData);
 
		}	
	}	
}