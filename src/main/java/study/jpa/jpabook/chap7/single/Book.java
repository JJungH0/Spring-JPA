package study.jpa.jpabook.chap7.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//@Entity
@DiscriminatorValue("B")
public class Book extends Item{
}
