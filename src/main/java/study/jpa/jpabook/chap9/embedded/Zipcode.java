package study.jpa.jpabook.chap9.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class Zipcode {
    String zip;
    String plusFour;
}
