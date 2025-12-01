package study.jpa.jpabook.chap8.cascade.chap1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Child {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Parent parent;
}
