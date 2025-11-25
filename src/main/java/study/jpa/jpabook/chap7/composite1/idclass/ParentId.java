package study.jpa.jpabook.chap7.composite1.idclass;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentId implements Serializable {
    private String id1; // Parent.id1 매핑
    private String id2; // Parent.id2 매핑
}
