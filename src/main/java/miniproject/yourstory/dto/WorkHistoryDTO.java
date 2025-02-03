package miniproject.yourstory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miniproject.yourstory.entity.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkHistoryDTO {
    private long workId;
    private long conditionId;
    private String title;
    private Period recruitment;
    private int period;
    private String org;
    private String day;
    private int elapsedTime;
}
