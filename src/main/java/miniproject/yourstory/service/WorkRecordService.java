package miniproject.yourstory.service;

import lombok.RequiredArgsConstructor;
import miniproject.yourstory.dto.WorkRecordDto;
import miniproject.yourstory.entity.Condition;
import miniproject.yourstory.entity.WorkRecord;
import miniproject.yourstory.repository.ConditionRepository;
import miniproject.yourstory.repository.WorkRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkRecordService {

    private final WorkRecordRepository workRecordRepository;
    private final ConditionRepository conditionRepository;

    public WorkRecordDto createWorkRecord(Long conditionId, LocalDate date, String content) {
        Condition condition = conditionRepository.findById(conditionId)
                .orElseThrow(() -> new IllegalArgumentException("Condition not found"));

        WorkRecord workRecord = new WorkRecord();
        workRecord.setCondition(condition);
        workRecord.setDate(date);
        workRecord.setContent(content);

        WorkRecord savedRecord = workRecordRepository.save(workRecord);
        return WorkRecordDto.fromEntity(savedRecord);
    }

    // conditionId와 date에 해당하는 WorkRecord 조회
    public List<WorkRecordDto> getWorkRecordsByConditionAndDate(Long conditionId, LocalDate date) {
        // conditionId와 date에 맞는 레코드 조회
        List<WorkRecord> workRecords = workRecordRepository.findAllByCondition_IdAndDate(conditionId, date);

        // 조회된 WorkRecord 엔티티를 DTO로 변환하여 반환
        return workRecords.stream()
                .map(WorkRecordDto::fromEntity)  // 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }
}
