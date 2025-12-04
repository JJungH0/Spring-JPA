package study.jpa.jpabook.chap10.jpql;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
//        jpqlUse(emf.createEntityManager());
//        criteriaUse(emf.createEntityManager());
//        querydsl(emf.createEntityManager());
        nativeSql(emf.createEntityManager());
    }

    static void jpqlUse(EntityManager em) {
        String jpql = "select m from Member m where m.name = 'kim'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
    }

    static void criteriaUse(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kim"));

        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    static void querydsl(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember member = QMember.member;

        List<Member> kim = query
                .select(member)
                .from(member)
                .where(member.name.eq("kim"))
                .fetch();
    }

    static void nativeSql(EntityManager em) {
        String sql = "SELECT MEMBER_ID, NAME FROM MEMBER WHERE NAME = 'kim'";
        List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
    }
}
