package study.jpa.jpabook.chap6.onetoone.oneway;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

//@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @OneToOne
    @JoinColumn(name = "locker_id",unique = true)
    private Locker locker;

    private String username;
}
