package miniproject.yourstory.repository;

import miniproject.yourstory.entity.Condition;
import miniproject.yourstory.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    // memberId를 직접 사용하여 조건 찾기
    List<Condition> findByMember(Member member);
    boolean existsByWorkIdAndMemberUsername(Long workId, String memberUsername); //중복체크
}
