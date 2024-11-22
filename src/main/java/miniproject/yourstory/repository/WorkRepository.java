package miniproject.yourstory.repository;

import miniproject.yourstory.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findAllByOrderByOrg(); // 지역 기준 정렬
    List<Work> findAllByOrderByState(); // 모집 상태 기준 정렬
    List<Work> findAllByOrderByDay(); // 요일 기준 정렬
}
