package study.jpa.jpabook.chap7.join;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//@Entity
@DiscriminatorValue("M")
public class Movie extends Item{
    private String director;
    private String actor;
}
