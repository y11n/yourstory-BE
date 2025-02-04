package miniproject.yourstory.controller;

import lombok.RequiredArgsConstructor;
import miniproject.yourstory.dto.ConditionDto;
import miniproject.yourstory.dto.WorkDTO;
import miniproject.yourstory.dto.WorkHistoryDTO;
import miniproject.yourstory.dto.WorkListDTO;
import miniproject.yourstory.entity.Condition;
import miniproject.yourstory.entity.Work;
import miniproject.yourstory.service.WorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;


    // 봉사 목록 조회
    @GetMapping
    public ResponseEntity<List<WorkListDTO>> getWorkList() {
        List<WorkListDTO> works = workService.getWorkList();
        return ResponseEntity.ok(works);
    }

    // 봉사 상세 조회
    @GetMapping("/{workId}")
    public ResponseEntity<WorkDTO> getWorkDetail(@PathVariable Long workId) {
        WorkDTO workDto = workService.getWorkDetail(workId);
        return ResponseEntity.ok(workDto);
    }

    // 봉사 신청
    @PostMapping("/{workId}")
    public ResponseEntity<ConditionDto> applyForWork(@PathVariable Long workId) {
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Condition condition = workService.applyForWork(workId, username);
        return ResponseEntity.ok(ConditionDto.fromEntity(condition));
    }

    // 나의 봉사 현황 조회
    @GetMapping("/my-status")
    public ResponseEntity<List<WorkHistoryDTO>> getMyWorkStatus() {
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<WorkHistoryDTO> history = workService.getMyWorkStatus(username);
        return ResponseEntity.ok(history);
    }


    // 지역/모집 상태/요일 필터링
    @GetMapping("/list")
    public ResponseEntity<List<WorkDTO>> filterWorkList(@RequestParam(required = false) String sort,
                                                        @RequestParam(required = false) String dayOfWeek,
                                                        @RequestParam(required = false) String regions,
                                                        @RequestParam(required = false) String recruitmentStatus) {
        List<Work> works = workService.filterWorkList(dayOfWeek, regions, recruitmentStatus);
        List<WorkDTO> workDTOs = works.stream().map(WorkDTO::fromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(workDTOs);
    }
}
