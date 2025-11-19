package study.jpa.jpabook.model.puremapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_name")
    private String username;

    @ManyToOne @Setter
    @JoinColumn(name = "team_id")
    private Team team;
}
