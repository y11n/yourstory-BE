package miniproject.yourstory.controller;

import miniproject.yourstory.dto.StoryListItemDTO;
import miniproject.yourstory.service.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List> getStoryList(){
        List<StoryListItemDTO> storyList = storyService.getList();
        return ResponseEntity.ok(storyList);
    };

}
