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
}
