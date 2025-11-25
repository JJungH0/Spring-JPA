//package study.jpa.jpabook.model.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import study.jpa.jpabook.model.status.OrderStatus;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//@Entity @Data
//@Table(name = "Orders")
//public class Order {
//
//    @Id @GeneratedValue
//    @Column(name = "oroder_id")
//    private Long orderId;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date orderDate; // 주문 날짜
//
//    /**
//     * EnumType.ORDINAL :
//     * - enum의 값 순서대로 0 ~ ..으로 표현
//     * - 내부 문자열로 표현하려면 String으로
//     */
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status; // 주문 상태
//
//    /**
//     * 연관관계 메서드
//     */
//    public void setMember(Member member) {
//        // 기존 관계 제거
//        if (Objects.nonNull(this.member)) {
//            this.member.getOrders().remove(this);
//        }
//        this.member = member;
//        member.getOrders().add(this);
//    }
//
//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//}
