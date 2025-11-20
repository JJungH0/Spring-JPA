package study.jpa.jpabook.chap6.manytomany.twoway;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    private String username;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            product.getMembers().add(this);
        }
    }
}
