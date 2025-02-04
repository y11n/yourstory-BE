package miniproject.yourstory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "volunteer_condition")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @ManyToOne(fetch = LAZY) // Member 엔티티와 관계 설정
    @JoinColumn(name = "member_username", referencedColumnName = "username") // 외래키로 username 사용
    private Member member; // Member 참조

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "work_id", nullable = false) // FK - 봉사활동 ID
    private Work work;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
