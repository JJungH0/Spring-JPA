package study.jpa.jpabook.chap10.dsl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import study.jpa.jpabook.chap10.jpql.Member;
import study.jpa.jpabook.chap10.jpql.QMember;
import study.jpa.jpabook.chap10.jpql.QProduct;
import study.jpa.jpabook.chap10.ployjpql.QItem;

import java.util.List;

public class Main {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
//        queryDsl(emf.createEntityManager());
        searchQuery(emf.createEntityManager());
    }

    static void queryDsl(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        List<Member> members = query
                .select(qMember)
                .from(qMember)
                .where(qMember.name.eq("회원1"))
                .orderBy(qMember.name.desc())
                .fetch();
    }

    static void searchQuery(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;

        query.select(product)
                .from(product)
                .where(product.name.eq("좋은 상품").and(product.price.gt(20000)))
                .fetch();
    }
}
