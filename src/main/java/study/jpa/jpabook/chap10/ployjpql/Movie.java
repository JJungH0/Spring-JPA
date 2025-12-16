package study.jpa.jpabook.chap10.ployjpql;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

//@Entity
@Data
@DiscriminatorValue("M")
public class Movie extends Item{
    private String name;
}
