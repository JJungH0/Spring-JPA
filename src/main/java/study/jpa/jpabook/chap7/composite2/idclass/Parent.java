package study.jpa.jpabook.chap7.composite2.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Entity
public class Parent {

    @Id @Column(name = "PARENT_ID")
    private String parentId;

    private String name;
}
