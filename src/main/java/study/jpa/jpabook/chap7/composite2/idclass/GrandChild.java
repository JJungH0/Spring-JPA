package study.jpa.jpabook.chap7.composite2.idclass;

import jakarta.persistence.*;

//@Entity
@IdClass(GrandChildId.class)
public class GrandChild {

    @Id @Column(name = "GRANDCHILD_ID")
    private String grandchildId;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child childId;

    private String name;
}
