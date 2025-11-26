package study.jpa.jpabook.chap7.onetomany275;

import jakarta.persistence.*;

//@Entity
public class Child {

    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;
}
