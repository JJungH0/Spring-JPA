package study.jpa.jpabook.chap10.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import study.jpa.jpabook.chap10.jpql.Member;
import study.jpa.jpabook.chap10.jpql.Team;
import study.jpa.jpabook.chap10.jpql.dto.UserDTO;

import java.util.List;

@Slf4j
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
//        find(emf.createEntityManager());
//        search(emf.createEntityManager());
//        reversSearch(emf.createEntityManager());
//        find2(emf.createEntityManager());
//        dtoFind(emf.createEntityManager());
//        groupByFind(emf.createEntityManager());
//        havingFind(emf.createEntityManager());
//        joinFind(emf.createEntityManager());
        subQuery(emf.createEntityManager());
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Team team1 = new Team();
        team1.setName("팀A");
        Team team2 = new Team();
        team2.setName("팀B");

        Member member1 = new Member();
        member1.setName("테스터1");
        member1.setAge(27);
        member1.setTeam(team1);

        Member member2 = new Member();
        member2.setName("테스터2");
        member2.setAge(28);
        member2.setTeam(team2);

        em.persist(team1);
        em.persist(team2);
        em.persist(member1);
        em.persist(member2);
        tr.commit();

    }

    static void find(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> from = query.from(Member.class);
        query.select(from);

        List<Member> resultList = em.createQuery(query).getResultList();

        for (Member member : resultList) {
            log.info("member = {}",member);
        }
    }

    static void search(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
        Root<Member> m = cq.from(Member.class);

        Predicate equal = cb.equal(m.get("name"), "테스터1");
        Order age = cb.desc(m.get("age"));

        cq.select(m)
                .where(equal)
                .orderBy(age);
        Member singleResult = em.createQuery(cq).getSingleResult();
        log.info("singleResult = {}",singleResult);
    }

    static void reversSearch(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);

        Predicate ageGt = cb.greaterThan(m.get("age"), 10);

        cq.select(m)
                .where(ageGt)
                .orderBy(cb.desc(m.get("age")));

        List<Member> resultList = em.createQuery(cq).getResultList();
        for (Member member : resultList) {
            log.info("member = {}",member);
        }
    }

    static void find2(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);

        cq.multiselect(m.get("name"), m.get("age")).distinct(true);

        List<Object[]> resultList = em.createQuery(cq).getResultList();
//        List<Object> resultList = em.createQuery(cq).getResultList();

        for (Object[] o : resultList) {
            String name = (String) o[0];
            Integer age = (Integer) o[1];
            log.info("name = {}", name);
            log.info("age = {}", age);
        }
    }

    static void dtoFind(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserDTO> cq = cb.createQuery(UserDTO.class);
        Root<Member> m = cq.from(Member.class);

        cq.select(cb.construct(UserDTO.class, m.get("name"), m.get("age")));

        List<UserDTO> resultList = em.createQuery(cq).getResultList();
        for (UserDTO userDTO : resultList) {
            log.info("userDTO = {}",userDTO);
        }
    }

    static void groupByFind(EntityManager em) {
        /**
         * JPQL :
         * SELECT m.team.name, max(m.age), min(m,age)
         * FROM Member m
         * GROUP BY m.team.name
         */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);

        Expression<Integer> maxAge = cb.max(m.<Integer>get("age"));
        Expression<Integer> minAge = cb.min(m.<Integer>get("age"));

        cq.multiselect(m.get("team").get("name"), maxAge, minAge)
                .groupBy(m.get("team").get("name"));

        List<Object[]> resultList = em.createQuery(cq).getResultList();
        for (Object[] objects : resultList) {
            String teamName = (String)objects[0];
            Integer memberMaxAge = (Integer) objects[1];
            Integer memberMinAge = (Integer) objects[2];

            log.info("teamName = {}, memberMaxAge = {}, memberMinAge = {}",teamName,memberMaxAge,memberMinAge);
        }
    }

    static void havingFind(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);

        Expression<Number> maxAge = cb.max(m.get("age"));
        Expression<Number> minAge = cb.min(m.get("age"));

        cq.multiselect(m.get("team").get("name"), maxAge, minAge)
                .groupBy(m.get("team").get("name"))
                .having(cb.greaterThan(m.get("age"), 10));

        List<Object[]> resultList = em.createQuery(cq).getResultList();

        for (Object[] objects : resultList) {
            String teamName = (String) objects[0];
            Integer age1 = (Integer) objects[1];
            Integer age2 = (Integer) objects[2];
            log.info("teamName = {}, maxAge = {}, minAge = {}",teamName,age1,age2);
        }
    }

    static void joinFind(EntityManager em) {
        /**
         * JPQL :
         * SELECT m, t
         * FROM Member m
         * join m.team t
         * WHERE t.name = '팀A'
         */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);
        Join<Object, Object> t = m.join("team", JoinType.INNER);
//        m.fetch("team");
        cq.multiselect(m, t)
                .where(cb.equal(t.get("name"), "팀A"));
//        cq.multiselect(m);
        em.createQuery(cq).getResultList();
    }

    static void subQuery(EntityManager em) {
        /**
         * JPQL :
         * SELECT m
         * FROM Member m
         * WHERE m.age >= (SELECT AVG(m2.age) FROM Member m2)
         */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> mainQuery = cb.createQuery(Member.class);

        Subquery<Double> subquery = mainQuery.subquery(Double.class);
        Root<Member> m2 = subquery.from(Member.class);
        subquery.select(cb.avg(m2.get("age")));

        Root<Member> m = mainQuery.from(Member.class);
        mainQuery.select(m)
                .where(cb.ge(m.get("age"), subquery));

        List<Member> resultList = em.createQuery(mainQuery).getResultList();
    }
}