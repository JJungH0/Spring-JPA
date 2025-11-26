package study.jpa.jpabook.chap7.onetomany275;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
public class Parent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID", referencedColumnName = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID", referencedColumnName = "CHILD_ID"))
    private List<Child> children = new ArrayList<>();
}
