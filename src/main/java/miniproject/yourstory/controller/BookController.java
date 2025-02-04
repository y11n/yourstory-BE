package miniproject.yourstory.controller;

import miniproject.yourstory.dto.BookListItemDTO;
import miniproject.yourstory.dto.BookResDTO;
import miniproject.yourstory.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List> getBookList(){
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<BookListItemDTO> bookList = bookService.getBookList(username);
        return ResponseEntity.ok(bookList);
    }

    // 도서 상세 조회
    @GetMapping("/{book_id}")
    public ResponseEntity<BookResDTO> getBook(@PathVariable long book_id){
        // 로그인한 사용자 username 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        BookResDTO book = bookService.getBook(book_id, username);
        return ResponseEntity.ok(book);
    }

    // pdf 조회
    @GetMapping("/pdf/{book_id}")
    public ResponseEntity<String> getPdf(@PathVariable long book_id){
        String pdf_path = bookService.getPdf(book_id);
        return ResponseEntity.ok(pdf_path);
    }

}
