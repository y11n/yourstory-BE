package miniproject.yourstory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Member {

    @Id
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;
}
