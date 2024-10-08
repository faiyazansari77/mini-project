package in.g77tech.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {
	
	private String planName;
	private String planStatus;
	private String gender;
	
	private String startDate;
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String endDate;

	

}
