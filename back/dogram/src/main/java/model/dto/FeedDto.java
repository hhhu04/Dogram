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
public class FeedDto {

	private Long num;

    private String img;

    private String title;

    private String content;

    private int commentCount;

    private int likeCount;

    private String category;

    private Long userNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String address;
    

	private Long communityNum;

	private Long userLikeNum;

	private String id;
	
}
