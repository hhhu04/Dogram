package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WantListDto {

	private Long num;
	
	private Long userNum;
	
	private Long storeNum;
	
	private String title;
	
}
