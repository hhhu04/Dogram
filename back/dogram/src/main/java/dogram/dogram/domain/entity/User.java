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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String id;

    private String password;

    private String petName;

    private String email;

    private String phoneNumber;

    private String img;

    private String address;

    private int creditRating;

    private String creditGrade;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
