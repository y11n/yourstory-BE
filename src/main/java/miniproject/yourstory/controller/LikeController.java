package miniproject.yourstory.controller;

import miniproject.yourstory.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 좋아요 생성
    @PostMapping("/{book_id}")
    public ResponseEntity<String> likePost(@PathVariable long book_id) {
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try{
            int currentLikes = likeService.insert(book_id, username);
            return ResponseEntity.ok(currentLikes + "");
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 좋아요 취소
    @DeleteMapping("/{book_id}")
    public ResponseEntity<String> unlikePost(@PathVariable long book_id) {
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try{
            int currentLikes = likeService.delete(book_id, username);
            return ResponseEntity.ok((currentLikes-1) + "");
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
