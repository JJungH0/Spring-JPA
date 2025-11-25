package study.jpa.jpabook.chap7.composite2.idclass;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class GrandChildId implements Serializable {

    private ChildId childId;

    private String grandchildId;
}
