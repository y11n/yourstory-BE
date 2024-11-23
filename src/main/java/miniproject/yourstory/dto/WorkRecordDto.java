package miniproject.yourstory.dto;

import lombok.Data;
import miniproject.yourstory.entity.WorkRecord;

import java.time.LocalDate;
import java.util.Date;

@Data
public class WorkRecordDto {
    private Long id;
    private Long conditionId;
    private LocalDate date;
    private String content;

    public static WorkRecordDto fromEntity(WorkRecord workRecord) {
        WorkRecordDto dto = new WorkRecordDto();
        dto.setId(workRecord.getId());
        dto.setConditionId(workRecord.getCondition().getId());
        dto.setDate(workRecord.getDate());
        dto.setContent(workRecord.getContent());
        return dto;
    }
}
