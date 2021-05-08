package model.dto;

import java.time.LocalDateTime;

import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long communityNum;

    private Long userNum;

    private String userId;

	
}
