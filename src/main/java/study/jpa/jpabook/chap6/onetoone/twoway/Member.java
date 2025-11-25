package study.jpa.jpabook.chap6.onetoone.twoway;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

//@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker lockerId;

    private String username;

}
