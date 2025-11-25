package study.jpa.jpabook.chap6.manytomany.onetwoway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

//@Entity @Data
public class Product {

    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
