package study.jpa.jpabook.chap7.composite2.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class ChildId implements Serializable {
    private String parentId; // @MapsId("parentId")로 매핑

    @Column(name = "CHILD_ID")
    private String id;
}
