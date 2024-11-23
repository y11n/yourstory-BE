package miniproject.yourstory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryResDTO {

    private int id;
    private String category;
    private String title;
    private String img;
    private String prologue;
    private String content;

}
