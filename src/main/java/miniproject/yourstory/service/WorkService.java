package miniproject.yourstory.service;

import lombok.RequiredArgsConstructor;
import miniproject.yourstory.entity.Condition;
import miniproject.yourstory.entity.Member;
import miniproject.yourstory.entity.Work;
import miniproject.yourstory.repository.ConditionRepository;
import miniproject.yourstory.repository.MemberRepository;
import miniproject.yourstory.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final ConditionRepository conditionRepository;
    private final MemberRepository memberRepository;

    // 봉사 목록 조회
    public List<Work> getWorkList() {
        return workRepository.findAll();
    }

    // 봉사 상세 조회
    public Work getWorkDetail(Long workId) {
        return workRepository.findById(workId)
                .orElseThrow(() -> new IllegalArgumentException("봉사 활동을 찾을 수 없습니다."));
    }

    // 봉사 신청
    public Condition applyForWork(Long workId, String memberId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new IllegalArgumentException("봉사 활동을 찾을 수 없습니다."));

        boolean alreadyApplied = conditionRepository.existsByWorkIdAndMemberUsername(workId, memberId);
        if (alreadyApplied) {
            throw new IllegalArgumentException("이미 해당 봉사 활동에 신청한 상태입니다.");
        }

        Member member = memberRepository.findByUsername(memberId);

        Condition condition = new Condition();
        condition.setWork(work);
        condition.setMember(member);
        condition.setPeriod(work.getPeriod());
        return conditionRepository.save(condition);
    }

    // 나의 봉사 현황 조회
    public List<Condition> getMyWorkStatus(String username) {
        // 회원 정보를 username을 통해 조회
        Member member = memberRepository.findByUsername(username);

        // 해당 회원의 봉사 활동 상태 조회
        return conditionRepository.findByMember(member); // memberId로 조건을 찾습니다.
    }

    // 지역/모집 상태/요일 필터링
    public List<Work> filterWorkList(String dayOfWeek, String regions, String recruitmentStatus) {
        return workRepository.filterWorkByConditions(dayOfWeek, regions, recruitmentStatus);
    }

}
