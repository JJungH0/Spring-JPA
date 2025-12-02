package study.jpa.jpabook.chap9.embedded;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Embedded Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
            @AttributeOverride(name = "state", column = @Column(name = "COMPANY_STATE")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE")),
            @AttributeOverride(name = "zipcode.zip", column = @Column(name = "COMPANY_ZIP")),
            @AttributeOverride(name = "zipcode.plusFour", column = @Column(name = "COMPANY_PLUSFOUR"))
    })
    Address companyAddress;

    @Embedded PhoneNumber phoneNumber;

    @Column(name = "MEMBER_NAME")
    private String name;
}
