package miniproject.yourstory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Member {

    @Id
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Letter> letters = new ArrayList<>();
}
