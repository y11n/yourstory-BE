package miniproject.yourstory.dto;

import lombok.Data;
import org.springframework.core.io.UrlResource;

@Data
public class BookListItemDTO {
    private Long id;
    private String addressee;
    private String title;
    private int likes;
    private int letters;
    private String imgPath;

    public BookListItemDTO(){}

    public BookListItemDTO(Long id, String addressee, String title, int likes, int letters, String imgPath) {
        this.id = id;
        this.addressee = addressee;
        this.title = title;
        this.likes = likes;
        this.imgPath = imgPath;
    }
}