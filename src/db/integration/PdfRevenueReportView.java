package db.integration;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Image;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.codec.GifImage;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import com.lowagie.text.pdf.codec.GifImage;
import java.io.FileOutputStream;

@Controller
public class PdfRevenueReportView extends AbstractPdfView{
	
	@Override
	@RequestMapping(value="/back-up")
	protected void buildPdfDocument(Map model, Document document,
		PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
System.out.println("########################################################################");
		Map<String,String> revenueData =model;// (Map<String,String>) model.get("revenueData");
		 System.out.println("Pdf  )))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))) " + revenueData);

		Table table = new Table(2);
		table.addCell("Month");
		table.addCell("Revenue");

		for (Map.Entry<String, String> entry : revenueData.entrySet()) {
			table.addCell(entry.getKey());
			table.addCell(entry.getValue());
               }
		//document.open();
		document.add(table);
		document.close();
	}

}