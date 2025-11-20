package study.jpa.jpabook.chap6.manytomany.onetwoway2;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_amount")
    private Long orderAmount;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ordered_at")
    private LocalDateTime orderDate;

    @PrePersist
    public void setOrderDate() {
        this.orderDate = LocalDateTime.now();
    }
}
