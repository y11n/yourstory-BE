package miniproject.yourstory.controller;

import miniproject.yourstory.dto.LetterReqDTO;
import miniproject.yourstory.service.LetterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/letter")
public class LetterController {

    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping("/{book_id}")
    public ResponseEntity<String> addLetter(@PathVariable long book_id, @RequestBody LetterReqDTO dto) {
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        letterService.create(book_id, username, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("편지가 생성 완료 되었습니다.");
    }
}
