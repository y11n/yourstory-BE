package miniproject.yourstory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class WorkRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "condition_id", nullable = false)
    private Condition condition;

    private LocalDate date;

    @Lob
    private String content;

}
