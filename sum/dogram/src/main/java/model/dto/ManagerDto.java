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
public class ManagerDto {
	
	private Long num;
	
	private String id;
	
	private String name;
	
	private String password;
	
	private String phoneNumber;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private String grade;


	

}
