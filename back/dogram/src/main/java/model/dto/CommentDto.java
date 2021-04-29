package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {

	
	private Long num;

    private String comment;

    private String createdAt;

    private String updatedAt;

    private Long communityNum;

    private Long userNum;

    private String userId;

	
}
