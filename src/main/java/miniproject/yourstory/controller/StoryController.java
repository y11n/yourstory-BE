package miniproject.yourstory.controller;

import miniproject.yourstory.dto.StoryListItemDTO;
import miniproject.yourstory.dto.StoryResDTO;
import miniproject.yourstory.service.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<List> getStoryList(){
        List<StoryListItemDTO> storyList = storyService.getList();
        return ResponseEntity.ok(storyList);
    };

    // 상세 조회
    @GetMapping("/{story_id}")
    public ResponseEntity<StoryResDTO> getStory(@PathVariable int story_id){
        StoryResDTO story = storyService.getStory(story_id);
        return ResponseEntity.ok(story);
    }

}
