package study.jpa.jpabook.chap8.lazyloading;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Setter
    @Column(name = "MEMBER_NAME")
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TEAM_ID", foreignKey = @ForeignKey(name = "FK_MEMBER_TEAM"))
    private Team team;

}
