package study.jpa.jpabook.chap7.composite2.embedded;

import jakarta.persistence.*;

//@Entity
public class GrandChild {

    @EmbeddedId
    private GrandChildId grandChildId;


    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")})
    private Child child;

    private String name;
}
