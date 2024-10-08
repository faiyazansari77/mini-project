package in.g77tech.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import in.g77tech.dto.SearchRequestDto;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.g77tech.dto.PlanDto;
import in.g77tech.entity.CitizenPlan;
import in.g77tech.repository.CitizenPlanRepository;
import in.g77tech.service.ReportService;
import in.g77tech.util.EmailUtils;
import in.g77tech.util.ExcelGenerator;
import in.g77tech.util.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{
	
	
	private CitizenPlanRepository repo;
	private ExcelGenerator excelGenerator;
	private PdfGenerator pdfGenerator;
	private EmailUtils emailUtils;

	public ReportServiceImpl(CitizenPlanRepository repo, ExcelGenerator excelGenerator, 
			PdfGenerator pdfGenerator, EmailUtils emailUtils) {
		super();
		this.repo = repo;
		this.excelGenerator = excelGenerator;
		this.pdfGenerator =pdfGenerator;
		this.emailUtils = emailUtils;
	}

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = repo.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequestDto request) {
//		return repo.findAll();
//		Implements dynamic query bcos it give all info but we need as per requirement 
		CitizenPlan entity = new CitizenPlan();
		if(request.getPlanName()!=null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if(request.getPlanStatus()!=null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(request.getGender()!=null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}
		if(request.getStartDate()!=null && !request.getStartDate().equals("")) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			converting string to localDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		if(request.getEndDate()!=null && !request.getEndDate().equals("")) {
			String startDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		
//		BeanUtils.copyProperties(request, entity);
		return repo.findAll(Example.of(entity));
		
	}

	

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f = new File("plans.xlsx");
		List<CitizenPlan> records = repo.findAll();
		excelGenerator.generateExcel(response, records, f);
		
		String to = "ansarifaiyaz300@gmail.com";
		String subject = "Citizen Data";
		String body = "here we are attaching file";
		
		emailUtils.sendMail(to, subject, body, f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("plans.pdf");
		List<CitizenPlan> citizenPlan = repo.findAll();
		pdfGenerator.generatePdf(response, citizenPlan, f);
		
		String to = "ansarifaiyaz300@gmail.com";
		String subject = "Citizen Data";
		String body = "Here we are attaching pdf file";
		
		emailUtils.sendMail(to, subject, body, f);
		f.delete();
		return true;
	}

	@Override
	public List<PlanDto> getNameStatus() {
		List<PlanDto> nameAndPlanStatus = repo.findPlanNameAndPlanStatus();
		
		return nameAndPlanStatus;
	}

	@Override
	public List<CitizenPlan> getAllCitizenInfo() {
		return repo.findAll();
	}

}
