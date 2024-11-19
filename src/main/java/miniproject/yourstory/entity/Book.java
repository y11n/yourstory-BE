package miniproject.yourstory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String addressee;

    private String writer;

    private String org;

    private String intro;

    @OneToMany(mappedBy = "book")
    private List<Likes> likes = new ArrayList<>();

    private int letters;

    private String imgPath;

    private String pdfPath;

    public int getLikesCount(){
        return likes.size();
    }

}
