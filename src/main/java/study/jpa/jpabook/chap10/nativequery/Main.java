package study.jpa.jpabook.chap10.nativequery;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import study.jpa.jpabook.chap10.jpql.Member;

import java.math.BigInteger;
import java.util.List;

@Slf4j
public class Main {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
//        sqlQuery1(emf.createEntityManager());
//        sqlQuery2(emf.createEntityManager());
//        sqlQuery3(emf.createEntityManager());
        sqlQuery4(emf.createEntityManager());
    }
    static void sqlQuery4(EntityManager em) {
        String sql = "SELECT MEMBER_ID, MEMBER_NAME, AGE, TEAM_ID FROM Member";
        em.createNativeQuery(sql, Member.class)
                .setFirstResult(10)
                .setMaxResults(20)
                .getResultList();
    }
    static void sqlQuery3(EntityManager em) {
        String sql = "SELECT m.MEMBER_ID, m.MEMBER_NAME, m.AGE, m.TEAM_ID, i.ORDER_COUNT " +
                "FROM Member m " +
                "LEFT JOIN (" +
                "SELECT o.MEMBER_ID, COUNT(*) AS ORDER_COUNT " +
                "FROM Orders o " +
                "GROUP BY o.MEMBER_ID) " +
                "i ON m.MEMBER_ID = i.MEMBER_ID";
        Query nativeQuery = em.createNativeQuery(sql, "memberWithOrderCount");
        List<Object[]> resultList = nativeQuery.getResultList();
        log.info("resultList.size = {}",resultList.size());
        for (Object[] row : resultList) {
            log.info("member = {}", (Member)row[0]);
            log.info("orderCount = {}", (Long) row[1]);
        }
    }
    static void sqlQuery2(EntityManager em) {
        String sql = "SELECT member_id, member_name, age, team_id " +
                "FROM Member WHERE age > ?";

        List<Object[]> resultList = em.createNativeQuery(sql)
                .setParameter(1, 10)
                .getResultList();

        for (Object[] objects : resultList) {
            log.info("id = {}",objects[0]);
            log.info("name = {}",objects[1]);
            log.info("age = {}",objects[2]);
            log.info("team_id = {}",objects[3]);
        }
    }

    static void sqlQuery1(EntityManager em) {
        String sql = "SELECT MEMBER_ID, AGE, MEMBER_NAME, TEAM_ID " +
                "FROM Member WHERE AGE > ?";

        List<Member> resultList = em.createNativeQuery(sql, Member.class)
                .setParameter(1, 20)
                .getResultList();

        for (Member member : resultList) {
            System.out.println(member);
        }
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();
        member1.setAge(30);
        member1.setName("테스터1");
        member2.setAge(29);
        member2.setName("테스터2");
        member3.setAge(28);
        member3.setName("테스터3");
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        tr.commit();

    }
}
