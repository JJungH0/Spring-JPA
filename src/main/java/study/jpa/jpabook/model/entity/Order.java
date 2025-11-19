package study.jpa.jpabook.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import study.jpa.jpabook.model.status.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Data
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "oroder_id")
    private Long orderId;

    @Column(name = "member_id")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate orderDate; // 주문 날짜

    /**
     * EnumType.ORDINAL :
     * - enum의 값 순서대로 0 ~ ..으로 표현
     * - 내부 문자열로 표현하려면 String으로
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태


}
