package miniproject.yourstory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miniproject.yourstory.entity.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkListDTO {

    private long id;
    private String title;
    private String state;
    private Period recruitment;
    private int period;
    private String org;
    private String day;
}
