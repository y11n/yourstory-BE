package miniproject.yourstory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetterResDTO {
    private long id;
    private String title;
    private String content;
    @JsonProperty("isMine")
    private boolean isMine;
}
