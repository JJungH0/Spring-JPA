package study.jpa.jpabook.start;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR", // 시퀀스 제너레이터 이름 (= JPA 내부용)
        sequenceName = "BOARD_SEQ", // 실제 DB 시퀀스 이름
        initialValue = 1, // 초기값
        allocationSize = 1 // 증가값 (= DB increment와 일치해야함)
)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR")
    private Integer id;

    private String name;
}
