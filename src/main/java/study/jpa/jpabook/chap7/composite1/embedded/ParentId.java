package study.jpa.jpabook.chap7.composite1.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
public class ParentId implements Serializable {

    @Column(name = "PARENT_ID1")
    private String id1;
    @Column(name = "PARENT_ID2")
    private String id2;
}
