package miniproject.yourstory.controller;

import miniproject.yourstory.dto.BookListItemDTO;
import miniproject.yourstory.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<BookListItemDTO> bookList = bookService.getBookList();
        return ResponseEntity.ok(bookList);
    }

}
