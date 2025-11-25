package study.jpa.jpabook.chap7.composite1.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ParentId.class)
public class Parent {
    @Id
    @Column(name = "PARNET_ID1")
    private String id1; // parentId.id1과 연결

    @Id
    @Column(name = "PARNET_ID2")
    private String id2; // parentId.id2와 연결

    private String name;

}
