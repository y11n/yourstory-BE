package miniproject.yourstory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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

    private int likes;

    private int letters;

    private String imgPath;

    private String pdfPath;

}
