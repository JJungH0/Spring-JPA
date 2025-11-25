package study.jpa.jpabook.chap7.composite2.idclass;

import jakarta.persistence.*;

//@Entity
@IdClass(ChildId.class)
public class Child {

    @Id @Column(name = "CHILD_ID")
    private String childId;

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parentId;

    private String name;

}
