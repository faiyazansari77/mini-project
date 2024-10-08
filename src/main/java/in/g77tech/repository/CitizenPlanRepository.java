package in.g77tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.g77tech.dto.PlanDto;
import in.g77tech.dto.PlanDto;
import in.g77tech.entity.CitizenPlan;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer>{
	
	@Query("select distinct (planName) from CitizenPlan")
	public List<String> getPlanNames();
	
	@Query("select distinct (planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
	
	@Query("SELECT DISTINCT new in.g77tech.dto.PlanDto(c.planName, c.planStatus) FROM CitizenPlan c")
	public List<PlanDto> findPlanNameAndPlanStatus();
	
	
}
