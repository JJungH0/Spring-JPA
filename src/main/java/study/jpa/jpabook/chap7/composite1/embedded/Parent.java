package study.jpa.jpabook.chap7.composite1.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

//@Entity
@Data
public class Parent {

    @EmbeddedId
    private ParentId id;

    private String name;
}
