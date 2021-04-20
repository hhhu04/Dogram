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
public class MessageLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private LocalDateTime createdAt;

    private Long writerNum;

    private Long readerNum;

    private String message;

    private String messageStatus;







}
