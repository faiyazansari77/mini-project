package in.g77tech.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.g77tech.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generatePdf(HttpServletResponse response, List<CitizenPlan> citizenPlan, File f) throws Exception{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.0f, 2.0f, 2.50f, 2.3f, 2.5f, 2.5f, 2.0f});
        table.setSpacingBefore(10);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font fontC = FontFactory.getFont(FontFactory.HELVETICA);
        fontC.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("ID", fontC));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Citizen Name", fontC));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Gender", fontC));
        table.addCell(cell); 
         
        cell.setPhrase(new Phrase("Plan Name", fontC));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Status", fontC));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Start Date", fontC));
        table.addCell(cell); 
        cell.setPhrase(new Phrase("End Date", fontC));
        table.addCell(cell); 
        cell.setPhrase(new Phrase("Benifit Amount", fontC));
        table.addCell(cell);
        
        
        for(CitizenPlan plan : citizenPlan) {
        	table.addCell(plan.getCitizenId()+"");
        	table.addCell(plan.getCitizenName());
        	table.addCell(plan.getGender());
        	table.addCell(plan.getPlanName());
        	table.addCell(plan.getPlanStatus());
        	table.addCell(String.valueOf(plan.getPlanStartDate()));
        	table.addCell(plan.getPlanEndDate()+"");
        	table.addCell(plan.getBenifitAmount()+"");
        	
        }
        
        document.add(table);
        
        document.close();
	}
}
