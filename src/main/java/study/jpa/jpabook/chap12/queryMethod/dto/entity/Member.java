package study.jpa.jpabook.chap12.queryMethod.dto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NamedQuery(
        name = "Member.findByMemberName",
        query = "select m from Member m where m.name = :memberName"
)
@NoArgsConstructor
@Getter @Setter
@ToString
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME")
    private String name;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Column(name = "MEMBER_AGE")
    private int age;

}
