package miniproject.yourstory.service;

import miniproject.yourstory.dto.LetterReqDTO;
import miniproject.yourstory.dto.LetterResDTO;
import miniproject.yourstory.entity.Book;
import miniproject.yourstory.entity.Letter;
import miniproject.yourstory.entity.Member;
import miniproject.yourstory.repository.BookRepository;
import miniproject.yourstory.repository.LetterRepository;
import miniproject.yourstory.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LetterService {

    private final LetterRepository letterRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LetterService(LetterRepository letterRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.letterRepository = letterRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    // 편지 생성 메소드
    public void create(long bookId, String username, LetterReqDTO dto) {
        Book book = bookRepository.findById(bookId);
        Member member = memberRepository.findByUsername(username);

        Letter letter = new Letter();
        letter.setBook(book);
        letter.setMember(member);
        letter.setTitle(dto.getTitle());
        letter.setContent(dto.getContent());
        letterRepository.save(letter);
    }

    // 편지 목록 조회 메소드
    public List<LetterResDTO> getLetters(long bookId, String username) {
        Book book = bookRepository.findById(bookId);

        // 책에 해당하는 편지 목록 검색
        List<Letter> letters = letterRepository.findByBookId(bookId);

        return letters.stream()
                .map(letter -> new LetterResDTO(
                        letter.getId(),
                        letter.getTitle(),
                        letter.getContent(),
                        letter.getMember().getUsername().equals(username)
                ))
                .collect(Collectors.toList());
    }

    // 편지 삭제 메소드
    public void delete(long letterId, String username) throws Exception{
        Letter letter = letterRepository.findById(letterId);

        // 로그인한 유저가 생성한 편지가 아닐 때
        if(!letter.getMember().getUsername().equals(username)) {
            throw new Exception("현재 사용자가 생성한 편지가 아닙니다.");
        }
        letterRepository.delete(letter);
    }
}
