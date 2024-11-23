package miniproject.yourstory.service;

import miniproject.yourstory.dto.StoryListItemDTO;
import miniproject.yourstory.dto.StoryResDTO;
import miniproject.yourstory.entity.Story;
import miniproject.yourstory.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryService {

    @Value("${server.url}")
    private String serverUrl;

    private StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    // 이야기 목록 조회
    public List<StoryListItemDTO> getList(){
        return storyRepository.findAll().stream()
                .map(story -> {
                    return new StoryListItemDTO(
                            story.getId(),
                            story.getCategory(),
                            (serverUrl + "/files/" + story.getImg()),
                            story.getTitle(),
                            ((story.getPrologue() == null) ? story.getContent() : story.getPrologue())
                    );
                })
                .collect(Collectors.toList());
    };

    // 이야기 상세 조회
    public StoryResDTO getStory(int storyId) {
        Story story = storyRepository.findById(storyId);
        return new StoryResDTO(
                story.getId(),
                story.getCategory(),
                story.getTitle(),
                (serverUrl + "/files/" + story.getImg()),
                story.getPrologue(),
                story.getContent()
        );
    }

}
