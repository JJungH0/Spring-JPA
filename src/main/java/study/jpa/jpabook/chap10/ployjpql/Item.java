package study.jpa.jpabook.chap10.ployjpql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
/**
 * 상속 전략 :
 * - SINGLE_TABLE (= 기본값)
 *  - 부모와 자식 엔티티 모두 하나의 테이블에 저장
 * - JOINED
 *  - 부모 테이블 + 자식 테이블, 조회할 때는 부모와 자식 테이블을 JOIN해서 데이터를 가져옴
 * - TABLE_PER_CLASS
 *  - 부모 테이블 없이, 자식 엔티티 각각 테이블을 독립적으로 구성
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**
 * 자식 테이블 구분을 위한 어노테이션
 */
@DiscriminatorColumn(name = "DTYPE")
@Getter
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;
}
