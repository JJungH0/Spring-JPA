package study.jpa.jpabook.chap8.proxy;

import jakarta.persistence.*;
import lombok.Data;

//@Entity
@Data
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @ManyToOne
    private Team team;

}
