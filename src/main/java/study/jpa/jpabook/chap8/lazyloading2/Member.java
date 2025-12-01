package study.jpa.jpabook.chap8.lazyloading2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private Integer age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID",foreignKey = @ForeignKey(name = "MEMBER_TEAM"))
    private Team team;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
