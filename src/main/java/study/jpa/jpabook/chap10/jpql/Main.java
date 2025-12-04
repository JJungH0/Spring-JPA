package study.jpa.jpabook.chap10.jpql;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;

@Slf4j
public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
//        jpqlUse(emf.createEntityManager());
//        criteriaUse(emf.createEntityManager());
//        querydsl(emf.createEntityManager());
//        nativeSql(emf.createEntityManager());
//        typeQuery(emf.createEntityManager());
//        query(emf.createEntityManager());
        save(emf.createEntityManager());
//        paramBinding(emf.createEntityManager());
        locationBinding(emf.createEntityManager());

    }

    static void locationBinding(EntityManager em) {
        Member result = em.createQuery("select m from Member m where m.name = ?1"
                        , Member.class)
                .setParameter(1, "junghwan")
                .getSingleResult();
        log.info("result = {}",result);
    }
    static void paramBinding(EntityManager em) {

        Member result = em.createQuery("select m from Member m where m.name = :userName",
                        Member.class)
                .setParameter("userName", "junghwan")
                .getSingleResult();


        log.info("result = {}",result);
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

    static void typeQuery(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.name = 'choi'"
                , Member.class);

        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            log.info("member = {}", member);
        }
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member1 = new Member();
        member1.setAge(27);
        member1.setName("junghwan");

        Member member2 = new Member();
        member2.setAge(28);
        member2.setName("ABC");
        em.persist(member1);
        em.persist(member2);
        tr.commit();
    }

    static void query(EntityManager em) {
        Query query = em.createQuery("select m.name, m.age from Member m");

        List<Object[]> resultList = query.getResultList();
        Object singleResult = query.getSingleResult();


        for (Object o : resultList) {
            Object[] result = (Object[]) o;
            log.info("result[0] = {} result[0].getClass = {}", result[0], result[0].getClass().getName());
            log.info("result[1] = {} result[1].getClass = {}", result[1], result[1].getClass().getName());
        }
    }
}
