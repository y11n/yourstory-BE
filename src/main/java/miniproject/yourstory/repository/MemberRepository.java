package miniproject.yourstory.repository;

import miniproject.yourstory.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Boolean existsByUsername(String username);
}
