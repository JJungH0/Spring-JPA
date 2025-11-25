package study.jpa.jpabook.chap6.onetoone.twoway;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Entity
@NoArgsConstructor
public class Locker {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long lockerId;

    @Column(name = "locker_name")
    private String lockerName;

    @OneToOne(mappedBy = "lockerId")
    private Member member;
}
