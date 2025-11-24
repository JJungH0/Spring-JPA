package study.jpa.jpabook.chap7.join;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}
