package model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommunityDto {

	
	private Long num;

    private String img;

    private String title;

    private String body;

    private int commentCount;

    private int likeCount;

    private String tag;

    private Long userNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String address;


    
    
}
