package in.g77tech.service;

import java.util.List;

import in.g77tech.dto.PlanDto;
import in.g77tech.entity.CitizenPlan;
import in.g77tech.dto.SearchRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequestDto searchRequest);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	
	public List<PlanDto> getNameStatus();
	public List<CitizenPlan> getAllCitizenInfo();

}
