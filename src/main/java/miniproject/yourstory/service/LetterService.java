package miniproject.yourstory.service;

import miniproject.yourstory.dto.LetterReqDTO;
import miniproject.yourstory.entity.Book;
import miniproject.yourstory.entity.Letter;
import miniproject.yourstory.entity.Member;
import miniproject.yourstory.repository.BookRepository;
import miniproject.yourstory.repository.LetterRepository;
import miniproject.yourstory.repository.MemberRepository;
import org.springframework.stereotype.Service;

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
}
