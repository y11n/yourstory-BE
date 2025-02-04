package miniproject.yourstory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miniproject.yourstory.entity.Condition;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDto {

    private Long id;
    private Long workId;
    private Long memberId;
    private LocalDateTime createdAt;

    // Condition 엔티티를 ConditionDTO로 변환하는 메서드
    public static ConditionDto fromEntity(Condition condition) {
        return new ConditionDto(
                condition.getId(),
                condition.getWork().getId(),
                condition.getId(),
                condition.getCreatedAt()
        );
    }
}
