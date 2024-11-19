package miniproject.yourstory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResDTO {
    private long id;
    private String img;
    private String addressee;
    private String title;
    @JsonProperty("isLike")
    private boolean isLike;
    private int liked;
    private String writer;
    private String org;
    private String intro;
    private List<LetterReqDTO> letterBox = new ArrayList<>();
}
