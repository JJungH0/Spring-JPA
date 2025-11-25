package study.jpa.jpabook.chap7.composite2.idclass;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ChildId implements Serializable {

//    @Column(name = "PARENT_ID")
    private String parentId;

//    @Column(name = "CHILD_ID")
    private String childId;

}
