package study.jpa.jpabook.chap9.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Address {
    String street;
    String city;
    String state;
    @Embedded Zipcode zipcode;
}
