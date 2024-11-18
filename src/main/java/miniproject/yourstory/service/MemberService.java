package miniproject.yourstory.service;

import jakarta.transaction.Transactional;
import miniproject.yourstory.dto.JoinDTO;
import miniproject.yourstory.entity.Member;
import miniproject.yourstory.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public boolean create(JoinDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        Boolean isExist = memberRepository.existsByUsername(username);
        if(isExist){
            return false;
        }

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(bCryptPasswordEncoder.encode(password));
        member.setNickname(dto.getNickname());

        memberRepository.save(member);
        return true;
    }
}
