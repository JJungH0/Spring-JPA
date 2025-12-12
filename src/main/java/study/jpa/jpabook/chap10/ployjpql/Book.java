package study.jpa.jpabook.chap10.ployjpql;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("B")
@ToString
@Getter @Setter
public class Book extends Item{
    private String author;
}
