package in.g77tech.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import in.g77tech.entity.CitizenPlan;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	
	public void generateExcel(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
//		Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-date"); // XSSFSheet 
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("gender");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		headerRow.createCell(7).setCellValue("Benifit Amount");
		
		
		int dataRowIndex = 1;
		for(CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getGender());
			
			if(plan.getPlanStatus()!=null && !plan.getPlanStatus().equals("")) {
				dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			}else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			if(plan.getPlanStartDate()!=null  && !plan.getPlanStartDate().equals(null)) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			if(plan.getPlanEndDate()!=null && !plan.getPlanEndDate().equals(null)) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			if(plan.getBenifitAmount() != null) {
				dataRow.createCell(7).setCellValue(plan.getBenifitAmount());
			}else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			
			
			dataRowIndex++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}
}
