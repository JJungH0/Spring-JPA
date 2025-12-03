package study.jpa.jpabook.chap9.valuetpyecolletion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
