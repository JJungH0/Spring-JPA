package study.jpa.jpabook.chap9.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {
    String areaCode;
    String localNumber;
    @ManyToOne
    @JoinColumn(name = "PHONE_SERVICE_PROVIDER_ID", foreignKey = @ForeignKey(name = "MEMBER_PROVIDER"))
    PhoneServiceProvider provider;
}
