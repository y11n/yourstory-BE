package miniproject.yourstory.repository;

import miniproject.yourstory.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Integer> {
}
