package study.jpa.jpabook.chap7.jointable;

import jakarta.persistence.*;

//@Entity
public class Parent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID",
            referencedColumnName = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private Child child;
}
