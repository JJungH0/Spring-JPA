package study.jpa.jpabook.chap6.manytomany.twoway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();
}
