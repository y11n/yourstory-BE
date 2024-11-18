package miniproject.yourstory.service;

import miniproject.yourstory.dto.CustomUserDetails;
import miniproject.yourstory.entity.Member;
import miniproject.yourstory.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        // 검증
        if(member != null){
            return new CustomUserDetails(member);
        }
        return null;
    }
}
