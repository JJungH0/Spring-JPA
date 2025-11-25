package study.jpa.jpabook.chap7.composite2.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity
@Getter
public class BoardDetail {

    @Id
    private Long boardId;

    @Setter
    @MapsId
    @OneToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Setter
    private String content;
}
