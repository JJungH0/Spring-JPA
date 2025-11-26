package study.jpa.jpabook.chap7.manytoone276;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;


    @OneToMany(mappedBy = "parent")
    private List<Child> childList = new ArrayList<>();
}
