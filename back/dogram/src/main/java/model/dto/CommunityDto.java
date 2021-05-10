package model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String content;

    private int commentCount;

    private int likeCount;

    private String category;

    private Long userNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String address;

    private List<LikeListDto> list;
    
    private List<CommentDto> clist;
    
    
}
