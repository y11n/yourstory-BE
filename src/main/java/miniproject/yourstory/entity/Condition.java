package miniproject.yourstory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "volunteer_condition")
@Getter
@Setter
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @ManyToOne // Member 엔티티와 관계 설정
    @JoinColumn(name = "member_username", referencedColumnName = "username") // 외래키로 username 사용
    private Member member; // Member 참조

    @ManyToOne
    @JoinColumn(name = "work_id", nullable = false) // FK - 봉사활동 ID
    private Work work;

    private int period; // 시작 후 기간

}
