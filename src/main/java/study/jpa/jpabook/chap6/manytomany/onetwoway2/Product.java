package study.jpa.jpabook.chap6.manytomany.onetwoway2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@NoArgsConstructor
@Data
public class Product {

    @Id @Column(name = "product_id")
    private String id;

    private String name;
}
