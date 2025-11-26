package study.jpa.jpabook.chap7.entitytotable;

import jakarta.persistence.*;

/**
 * @SecondaryTable :
 *  - 해당 엔티티는 추가적인 보조 테이블 (= BOARD_DETAIL)도 함께 사용
 *  - 즉, 하나의 Board 객체를 저장하면 두 개의 테이블에 나뉘어서 저장
 * pkJoinColumns :
 *  - 현 엔티티 BOARD 테이블의 PK와 BOARD_DETAIL 테이블의 BOARD_DETAIL_ID의 PK가 같은 값으로 매핑
 */
@Entity
@Table(name = "BOARD")
@SecondaryTable(name = "BOARD_DETAIL",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "BOARD_DETAIL_ID"))

public class Board {

    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;

    // table = "BOARD_DETAIL"로 줬기 떄문에 보조 테이블인 디테일쪽에 컬럼 추가
    @Column(table = "BOARD_DETAIL")
    private String content;
}
