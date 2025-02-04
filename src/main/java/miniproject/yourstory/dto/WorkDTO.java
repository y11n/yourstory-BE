package miniproject.yourstory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miniproject.yourstory.entity.Period;
import miniproject.yourstory.entity.Time;
import miniproject.yourstory.entity.Work;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {

    private Long id;
    private String title;
    private String state;
    private Period recruitment;
    private int period;
    private String org;
    private String day;
    private String place;
    private String manager;
    private String content;
    private int person;
    private Time time;
    private String etc;

    // Work 엔티티를 WorkDTO로 변환하는 메서드
    public static WorkDTO fromEntity(Work work) {
        return new WorkDTO(
                work.getId(),
                work.getTitle(),
                work.getState(),
                work.getRecruitmentPeriod(),
                work.getWorkPeriod().months(),
                work.getOrg(),
                work.getDay(),
                work.getPlace(),
                work.getManager(),
                work.getContent(),
                work.getPerson(),
                work.getTime(),
                work.getEtc()
        );
    }
}
