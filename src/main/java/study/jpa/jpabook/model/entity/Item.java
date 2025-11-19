package study.jpa.jpabook.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private int itemPrice; // 상품 가격

    @Column(name = "stock_quantity")
    private int stockQuantity; // 재고 수량
}
