package miniproject.yourstory.controller;

import lombok.RequiredArgsConstructor;
import miniproject.yourstory.dto.WorkRecordDto;
import miniproject.yourstory.service.WorkRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work/record")
public class WorkRecordController {

    private final WorkRecordService workRecordService;

    @PostMapping
    public ResponseEntity<WorkRecordDto> createWorkRecord(@RequestBody WorkRecordDto workRecordDto) {
        WorkRecordDto workRecord = workRecordService.createWorkRecord(workRecordDto.getConditionId(), workRecordDto.getDate(), workRecordDto.getContent());
        return ResponseEntity.ok(workRecord);
    }


    // conditionId와 date에 맞는 WorkRecord 조회
    @GetMapping("/by-condition-and-date")
    public ResponseEntity<List<WorkRecordDto>> getWorkRecordsByConditionAndDate(
            @RequestParam Long conditionId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<WorkRecordDto> records = workRecordService.getWorkRecordsByConditionAndDate(conditionId, date);
        return ResponseEntity.ok(records);
    }
}
