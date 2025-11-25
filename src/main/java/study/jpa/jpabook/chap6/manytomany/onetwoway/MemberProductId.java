package study.jpa.jpabook.chap6.manytomany.onetwoway;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

//@Data
public class MemberProductId implements Serializable {
    private String member;
    private String product;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberProductId that = (MemberProductId) o;
        return Objects.equals(member, that.member) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }
}
