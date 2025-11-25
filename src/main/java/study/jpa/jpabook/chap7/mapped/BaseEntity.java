package study.jpa.jpabook.chap7.mapped;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

//@MappedSuperclass
public abstract class BaseEntity {
    @Id @GeneratedValue
    private String id;

    private String name;
}
