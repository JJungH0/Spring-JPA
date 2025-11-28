package study.jpa.jpabook.chap7.manytomany;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private List<Child> childList = new ArrayList<>();
}
