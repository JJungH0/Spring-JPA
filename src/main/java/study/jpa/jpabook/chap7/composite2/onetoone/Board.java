package study.jpa.jpabook.chap7.composite2.onetoone;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity
@ToString
@Getter
public class Board {

    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    @Setter
    private String title;

    @OneToOne(mappedBy = "board")
    private BoardDetail boardDetail;
}
