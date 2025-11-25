package study.jpa.jpabook.chap7.mapped;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "SELLER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "SELLER_NAME"))
})
public class Seller extends BaseEntity{
    private String shopName;
}
