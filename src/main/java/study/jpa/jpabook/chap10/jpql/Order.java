package study.jpa.jpabook.chap10.jpql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@ToString
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDER_AMOUNT")
    private int orderAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ORDER_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ORDER_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "ORDER_ZIPCODE"))}
    )
    private Address address;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", foreignKey = @ForeignKey(name = "ORDER_MEMBER_ID"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "ORDER_PRODUCT_ID"))
    private Product product;
}
