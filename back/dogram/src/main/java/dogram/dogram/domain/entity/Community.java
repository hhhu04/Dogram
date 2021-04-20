package dogram.dogram.domain.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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




}
