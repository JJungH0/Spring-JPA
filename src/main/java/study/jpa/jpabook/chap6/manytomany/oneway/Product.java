package study.jpa.jpabook.chap6.manytomany.oneway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;

    private String name;
}
