package in.g77tech.controller;

import java.util.List;

import in.g77tech.dto.SearchRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.g77tech.dto.PlanDto;
import in.g77tech.entity.CitizenPlan;
import in.g77tech.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReportRestController {
	
	@Autowired
	private ReportService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@GetMapping("/drop_info")
	public List<PlanDto> getPlanNameStatus(){
        return service.getNameStatus();
	}
	
	@GetMapping
	public List<CitizenPlan> getAllCitizenInfo(){
		return service.getAllCitizenInfo();
	}
	
	@PostMapping
	public List<CitizenPlan> searchCitizen(@RequestBody SearchRequestDto request){
		return service.search(request);
	}
	
	
	@GetMapping("/excel")
	public String exportExcel(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=plans.xlsx");
		
		boolean b = service.exportExcel(response);
		if(b){
			return "Excel have been downloads";
		}else{
			return "Failed";
		}
	}
	
	@GetMapping("/pdf")
	public String exportPdf(HttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=plans.pdf");
		service.exportPdf(response);
		return "Pdf have been downloads";
	}
	
	
	
}
