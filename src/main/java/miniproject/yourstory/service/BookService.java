package miniproject.yourstory.service;

import miniproject.yourstory.dto.BookListItemDTO;
import miniproject.yourstory.dto.BookResDTO;
import miniproject.yourstory.dto.LetterReqDTO;
import miniproject.yourstory.entity.Book;
import miniproject.yourstory.entity.Letter;
import miniproject.yourstory.repository.BookRepository;
import miniproject.yourstory.repository.LetterRepository;
import miniproject.yourstory.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Value("${server.url}")
    private String serverUrl;

    private BookRepository bookRepository;
    private LikeRepository likeRepository;
    private LetterRepository letterRepository;
    public BookService(BookRepository bookRepository, LikeRepository likeRepository, LetterRepository letterRepository) {
        this.bookRepository = bookRepository;
        this.likeRepository = likeRepository;
        this.letterRepository = letterRepository;
    }

    // 도서 목록 조회
    public List<BookListItemDTO> getBookList(String username){
        return bookRepository.findAll().stream()
                .map(book -> {
                    try{
                        return new BookListItemDTO(
                                book.getId(),
                                book.getAddressee(),
                                book.getTitle(),
                                ((likeRepository.findByBookIdAndMemberUsername(book.getId(), username) != null) ? true : false),
                                book.getLikesCount(),
                                book.getLettersCount(),
                                (serverUrl + "/files/" + book.getImgPath())
                        );
                    } catch (Exception e) {
                        throw new RuntimeException("Invalid file path: " + book.getImgPath(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    public BookResDTO getBook(long bookId, String username) {
        // 좋아요 여부
        boolean isLike = (likeRepository.findByBookIdAndMemberUsername(bookId, username) != null) ? true : false;
        // 책에 해당하는 편지 목록 검색
        List<Letter> letters = letterRepository.findByBookId(bookId);
        List<LetterReqDTO> letterBox = letters.stream()
                .map(letter -> new LetterReqDTO(
                        letter.getTitle(),
                        letter.getContent()
                ))
                .collect(Collectors.toList());

        Book book = bookRepository.findById(bookId);

        return new BookResDTO(
                book.getId(),
                (serverUrl + "/files/" + book.getImgPath()),
                book.getAddressee(),
                book.getTitle(),
                isLike,
                book.getLikesCount(),
                book.getWriter(),
                book.getOrg(),
                book.getIntro(),
                letterBox
        );
    }

    public String getPdf(long bookId) {
        Book book = bookRepository.findById(bookId);
        return serverUrl + "/files/" + book.getPdfPath();
    }
}
