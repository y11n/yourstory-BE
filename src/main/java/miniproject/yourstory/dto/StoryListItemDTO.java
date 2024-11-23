package miniproject.yourstory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryListItemDTO {

    private int id;
    private String category;
    private String img;
    private String title;
    private String content;

}
