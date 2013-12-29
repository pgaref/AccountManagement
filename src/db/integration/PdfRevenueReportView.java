package db.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class PdfRevenueReportView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Customer> revenueData  =(List<Customer>) model.get("revenueData");
		
		

	document.add((Element) new Paragraph("                 Customer List of Web Easy", FontFactory.getFont(FontFactory.COURIER, 15, Font.BOLD,	new CMYKColor(10, 100, 100, 0))));
		
	
		Table tables = new Table(2);
		float[] columnWidths = new float[] {100f, 100f};
		tables.setWidths(columnWidths);
		for (int i = 0; i < revenueData.size(); i++) {
			if (i % 2 == 0) {
				tables = new Table(2);
				
				tables.setWidths(columnWidths);
			}
			
			
			Paragraph p= new Paragraph(revenueData.get(i).print_it(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD,	new CMYKColor(100, 100, 100, 0)));
			//Paragraph p1= new Paragraph(Chunk.NEWLINE);

			tables.addCell(p);
			//tables.addCell(p1);
			if (i % 2 != 0) {
			document.add(tables);
			}
			//document.add(Chunk.NEWLINE);
			//Map<String, String > each_customer= new HashMap<String,String>();
			
			/*each_customer.put("Name",revenueData.get(i).getName() );
			each_customer.put("Domain",revenueData.get(i).getDomain() );
			each_customer.put("Email",revenueData.get(i).getEmail() );
			each_customer.put("Phone",revenueData.get(i).getMobile() );
			each_customer.put("Expiration Date",revenueData.get(i).getExpDate().toString() );
			each_customer.put("Charge",revenueData.get(i).getCharge().toString() );*/
			
			/*for (Map.Entry<String, String> entry : each_customer.entrySet()) {
				
				//table.addCell(entry.getKey());
				table.addCell(entry.getValue());
				
	        }*/
			
		}
		
		
	}
}