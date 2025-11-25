package study.jpa.jpabook.chap7.composite2.non;

import jakarta.persistence.*;

//@Entity
public class Grandchild {

    @Id @GeneratedValue
    @Column(name = "GRANDCHILD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Child child;

    private String name;
}
