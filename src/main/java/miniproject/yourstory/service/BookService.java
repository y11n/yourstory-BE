package miniproject.yourstory.service;

import miniproject.yourstory.dto.BookListItemDTO;
import miniproject.yourstory.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Value("${server.url}")
    private String serverUrl;

    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookListItemDTO> getBookList(){
        return bookRepository.findAll().stream()
                .map(book -> {
                    try{
                        return new BookListItemDTO(
                                book.getId(),
                                book.getAddressee(),
                                book.getTitle(),
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
}
