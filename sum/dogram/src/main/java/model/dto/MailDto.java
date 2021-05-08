package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
	
	private String email;
	
	private String id;
	
	private String password;
	
	private String from;
	
	private String subject;
	
	private String content;

}
