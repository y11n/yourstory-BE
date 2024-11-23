package miniproject.yourstory.repository;

import miniproject.yourstory.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    // 다중 조건 필터링
    @Query("SELECT w FROM Work w " +
            "WHERE (:day IS NULL OR w.day = :day) " +
            "AND (:regions IS NULL OR " +
            "     w.org LIKE %:regions%) " + // 부분 일치로 LIKE 사용
            "AND (:state IS NULL OR w.state = :state)")
    List<Work> filterWorkByConditions(
            @Param("day") String day,
            @Param("regions") String regions, // regions를 String으로 수정
            @Param("state") String state
    );


}
