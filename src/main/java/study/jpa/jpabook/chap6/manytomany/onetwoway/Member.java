package study.jpa.jpabook.chap6.manytomany.onetwoway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

/**
 * 회원과 회원 상품의 관계는 양방향 관계 : (1 : N)
 * -> 회원상품 (= MemberProduct)엔티티 쪽이 외래 키를 가지고 있으므로 연관관계의 주인임
 * -> 따라서 연관관계의 주인이 아닌 회원의 매핑 필드에는 mappedBy를 사용
 */
//@Entity @Data
public class Member {
    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts;
}
