package miniproject.yourstory.repository;

import miniproject.yourstory.entity.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {
    // 특정 conditionId와 특정 날짜에 해당하는 레코드를 조회
    List<WorkRecord> findAllByCondition_IdAndDate(Long conditionId, LocalDate date);
}
