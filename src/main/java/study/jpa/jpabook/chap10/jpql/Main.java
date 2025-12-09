package study.jpa.jpabook.chap10.jpql;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import study.jpa.jpabook.chap10.jpql.dto.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
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
//        locationBinding(emf.createEntityManager());
//        test(emf.createEntityManager());
//        pagination(emf.createEntityManager());
//        jpqlJoin(emf.createEntityManager());
//        jpqlJoin2(emf.createEntityManager());
//        jpqlJoin3(emf.createEntityManager());
//        fetchJoin(emf.createEntityManager());
//        fetchJoin2(emf.createEntityManager());
//        collectionFetchJoin(emf.createEntityManager());
        innerJoin(emf.createEntityManager());
    }

    static void innerJoin(EntityManager em) {
        List<Team> resultList = em.createQuery("SELECT t FROM Team t JOIN t.members WHERE t.name = '팀A'"
                        , Team.class)
                .getResultList();

        em.createQuery("SELECT SIZE(t.members) FROM Team t")
                .getSingleResult();
    }

    static void collectionFetchJoin(EntityManager em) {
        List<Team> resultList = em.createQuery("SELECT DISTINCT t FROM Team t JOIN FETCH t.members WHERE t.name = :teamName", Team.class)
                .setParameter("teamName", "팀A")
                .getResultList();

        log.info("resultList.size() = {}",resultList.size());

        for (Team team : resultList) {
            log.info("team.getName() = {}, team = {}", team.getName(), team);
            for (Member member : team.getMembers()) {
                log.info("member.getName() = {}, member = {}",member.getName(), member);
            }
        }
    }

    static void fetchJoin2(EntityManager em) {
        List<Team> resultList = em.createQuery("SELECT t FROM Team t JOIN FETCH t.members WHERE t.name = '팀A'", Team.class)
                .getResultList();
        log.info("resultList.getClass() = {}",resultList.getClass().getName());
    }
    static void fetchJoin(EntityManager em) {
        List<Member> resultList = em.createQuery("SELECT m FROM Member m JOIN FETCH m.team", Member.class)
                .getResultList();
        log.info("resultList.getClass() = {}", resultList.getClass().getName());
        for (Member member : resultList) {
            // 패치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안함
            log.info("member.getClass() = {}", member.getClass().getName());
            log.info("userName = {}, teamName = {}",member.getName(),member.getTeam().getName());
        }
//        Member member = em.find(Member.class, 1L);
//        log.info("member = {}",member);
    }
    static void jpqlJoin(EntityManager em) {

        String query = "SELECT m FROM Member m INNER JOIN m.team t WHERE t.name = :teamName";

        List<Member> resultList = em.createQuery(query, Member.class)
                .setParameter("teamName", "팀A")
                .getResultList();

        log.info("resultList = {}",resultList);
    }

    static void jpqlJoin2(EntityManager em) {
        String query = "SELECT m, t FROM Member m JOIN m.team t WHERE t.name = :teamName ORDER BY m.age";

        List<Object[]> resultList = em.createQuery(query)
                .setParameter("teamName","팀A")
                .getResultList();

        for (Object[] objects : resultList) {
            Member member = (Member) objects[0];
            Team team = (Team) objects[1];
            log.info("member = {}", member);
            log.info("team = {}", team);
        }
    }

    static void jpqlJoin3(EntityManager em) {
        String query = "SELECT m, t FROM Member m LEFT JOIN m.team t";
        List resultList = em.createQuery(query)
                .getResultList();
//        List<Member> resultList = em.createQuery(query, Member.class)
//                .getResultList();

    }
    static void  test(EntityManager em) {
        TypedQuery<UserDTO> query = em.createQuery("select new study.jpa.jpabook.chap10.jpql.dto.UserDTO(m.name, m.age) from Member m"
                , UserDTO.class);
        List<UserDTO> resultList = query.getResultList();
//        List<UserDTO> resultList = em.createQuery("select m.name, m.age from Member m",
//                UserDTO.class).getResultList();

        for (UserDTO userDTO : resultList) {
            log.info("userDTO = {}", userDTO);
        }
//        ArrayList<UserDTO> userDTOS = new ArrayList<>();
//
//        List<Object[]> resultList = em.createQuery("select m.name, m.age from Member m")
//                .getResultList();
//
//        for (Object[] objects : resultList) {
//            UserDTO userDTO = new UserDTO((String) objects[0], (Integer) objects[1]);
//            userDTOS.add(userDTO);
//        }
//
//        for (UserDTO userDTO : userDTOS) {
//            log.info("userDTO = {}", userDTO);
//        }
//        String sql = "SELECT o.address FROM Order o";
//        List<Address> resultList = em.createQuery(sql, Address.class).getResultList();
//        List<String> resultList = em.createQuery("SELECT DISTINCT m.name FROM Member m", String.class).getResultList();
//        for (String s : resultList) {
//            log.info("s = {}",s);
//        }
//        em.createQuery("select avg (o.orderAmount) from Order o", Double.class)
//                .getSingleResult();

//        List<Object[]> resultList = em.createQuery("select m.name, m.age from Member m")
//                .getResultList();
//        for (Object[] objects : resultList) {
//            log.info("{}",(String)objects[0]);
//            log.info("{}",(Integer)objects[1]);
//        }

//        List<Object[]> resultList = em.createQuery("select o.member, o.product, o.orderAmount from Order o")
//                .getResultList();
//        for (Object[] objects : resultList) {
//            Member member = (Member) objects[0];
//            Product product = (Product) objects[1];
//            Integer orderAmount = (Integer) objects[2];
//        }
//        Iterator iterator = resultList.iterator();
//        while (iterator.hasNext()) {
//            Object[] next = (Object[]) iterator.next();
//            log.info("next[0] = {}", next[0]);
//            log.info("next[1] = {}", next[1]);
//        }
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
        Team team = new Team();
        team.setName("팀A");
        Member member1 = new Member();
        member1.setAge(27);
        member1.setName("junghwan");
        member1.setTeam(team);

        Member member2 = new Member();
        member2.setAge(28);
        member2.setName("ABC");
        member2.setTeam(team);
        em.persist(team);
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

    static void pagination(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("select m from Member m order by m.age desc"
                , Member.class);

        query.setFirstResult(10); // 0부터 시작이니 10 -> 11번째 부터 시작
        query.setMaxResults(20); // 11번 포함 해서 20개를 출력 -> 30번째 데이터 조회
        query.getResultList();
    }
}
