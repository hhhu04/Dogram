package model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreDto {
	
	private Long num;
	
	private Long userNum;
	
	private Long buyer;
	
	private String category;

	private String price;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private String product;
	
	private int wantCount;
	
	private String title;
	
	

}
