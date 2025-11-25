package study.jpa.jpabook.chap7.composite2.embedded;

import jakarta.persistence.*;

//@Entity
public class Child {

    /**
     * 해당 엔티티의 PK는 ChildId라는 값 객체라고 선언
     */
    @EmbeddedId
    private ChildId id;

    @MapsId("parentId") // ChildId.parentId - Parent엔티티의 PK를 재사용
    @ManyToOne
    @JoinColumn(name = "PARENT_ID") // PARENT_ID라는 FK컬럼을 만듦
    public Parent parent;

    private String name;
}
