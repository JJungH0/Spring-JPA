package study.jpa.jpabook.chap8.lazyloading2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "ORDER_PRODUCT"))
    private Product product;

}
