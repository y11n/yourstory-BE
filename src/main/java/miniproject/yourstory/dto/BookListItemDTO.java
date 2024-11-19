package miniproject.yourstory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.core.io.UrlResource;

@Data
public class BookListItemDTO {
    private Long id;
    private String addressee;
    private String title;
    @JsonProperty("isLike")
    boolean isLike;
    private int likes;
    private int letters;
    private String imgPath;

    public BookListItemDTO(){}

    public BookListItemDTO(Long id, String addressee, String title, boolean isLike, int likes, int letters, String imgPath) {
        this.id = id;
        this.addressee = addressee;
        this.title = title;
        this.isLike = isLike;
        this.likes = likes;
        this.letters = letters;
        this.imgPath = imgPath;
    }
}
