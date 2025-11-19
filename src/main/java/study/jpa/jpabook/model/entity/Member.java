package study.jpa.jpabook.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Member {

    /**
     * 기본 키 생성 전략 :
     * - AUTO
     * H2 :
     * - Sequence
     */
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Setter
    @Column(name = "member_name")
    private String name;

    @Setter
    private String city;

    @Setter
    private String street;

    @Setter
    private String zipcode;
}
