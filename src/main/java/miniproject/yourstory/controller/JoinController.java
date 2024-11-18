package miniproject.yourstory.controller;

import miniproject.yourstory.dto.JoinDTO;
import miniproject.yourstory.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
    private final MemberService memberService;

    public JoinController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDTO dto){
        // 아이디와 아이디 확인이 일치하지 않음
        if(!dto.getUsername().equals(dto.getUsername2())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usernames do not match");
        }
        // 비밀번호와 비밀번호 확인이 일치하지 않음
        if(!dto.getPassword().equals(dto.getPassword2())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Passwords do not match");
        }

        if(memberService.create(dto)){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created successfully");
        }else{
            // username이 이미 존재
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The username already exists");
        }
    }
}

