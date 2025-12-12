package study.jpa.jpabook.chap10.jpql;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import study.jpa.jpabook.chap10.jpql.dto.UserDTO;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
//        innerJoin(emf.createEntityManager());
//        subQuery(emf.createEntityManager());
//        subQuery2(emf.createEntityManager());
//        subQuery3(emf.createEntityManager());
//        subQuery4(emf.createEntityManager());
//        collectionFetchJoin(emf.createEntityManager());
//        collectionCompare(emf.createEntityManager());
//        collectionMemberCompare(emf.createEntityManager());
//        test2(emf.createEntityManager());
//        caseQuery1(emf.createEntityManager());
//        caseQuery2(emf.createEntityManager());
//        test3(emf.createEntityManager());
//        test4(emf.createEntityManager());
//        test5(emf.createEntityManager());
//        test6(emf.createEntityManager());
//        test7(emf.createEntityManager());
        namedQuery(emf.createEntityManager());

    }

    static void namedQuery(EntityManager em) {
        Member member = em.createNamedQuery("Member.findByName", Member.class)
                .setParameter("paramName", "junghwan")
                .getSingleResult();

        Long singleResult = em.createNamedQuery("Member.count", Long.class).getSingleResult();
        log.info("result = {}",singleResult);
        log.info("member = {}",member);
    }
    static void test7(EntityManager em) {
        Team team = em.find(Team.class, 1L);
        em.createQuery("SELECT m FROM Member m WHERE m.team = :team", Member.class)
                .setParameter("team",team)
                .getResultList();

        em.createQuery("SELECT m FROM Member m WHERE m.team.id = :teamId", Member.class)
                .setParameter("teamId", team.getId())
                .getResultList();
    }
    static void test6(EntityManager em) {
        Member member = em.find(Member.class, 1L);

        em.createQuery("SELECT m FROM Member m WHERE m = :member")
                .setParameter("member", member)
                .getResultList();
    }
    static void test5(EntityManager em) {
        em.createQuery("SELECT COUNT(m.id) FROM Member m").getResultList();
        em.createQuery("SELECT COUNT(m) FROM Member m").getResultList();
    }

    static void test4(EntityManager em) {
        List<String> resultList = em.createQuery("SELECT NULLIF(m.name, 'junghwan')FROM Member m", String.class).getResultList();
        for (String s : resultList) {
            log.info("s = {}",s);
        }
    }
    static void test3(EntityManager em) {
        List<String> resultList = em.createQuery("SELECT COALESCE(m.name, '이름 없는 회원')FROM Member m", String.class).getResultList();
        for (String s : resultList) {
            log.info("s = {}", s);
        }
    }

    static void caseQuery2(EntityManager em) {
        List<String> resultList = em.createQuery(
                "SELECT " +
                        "CASE t.name " +
                        "WHEN '팀A' THEN '인센티브110%' " +
                        "WHEN '팀B' THEN '인센티브120%' " +
                        "ELSE '인센티브105%' END FROM Team t", String.class)
                .getResultList();

        for (String s : resultList) {
            log.info("s = {}",s);
        }
    }
    static void caseQuery1(EntityManager em) {
        List<String> resultList = em.createQuery("SELECT CASE WHEN m.age <= 10 THEN '학생요금' WHEN m.age >= 60 THEN '경로요금' ELSE '일반요금' END FROM Member m", String.class)
                .getResultList();
        for (String re : resultList) {
            log.info("re = {}", re);
        }
    }
    static void test2(EntityManager em) {
        List<Object[]> resultList = em.createQuery("SELECT CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP FROM Team", Object[].class)
                .getResultList();

        for (Object[] objects : resultList) {
            Date date = (Date) objects[0];
            Time time = (Time) objects[1];
            Timestamp timestamp = (Timestamp) objects[2];
            log.info("DATE = {}, TIME = {}, TIMESTAMP = {}", date,time,timestamp);
        }

    }

    static void collectionMemberCompare(EntityManager em) {
        Member member = em.find(Member.class, 1L);

        Team resultList = em.createQuery("SELECT t FROM Team t WHERE :memberParam MEMBER of t.members", Team.class)
                .setParameter("memberParam", member)
                .getSingleResult();
        log.info("resultList = {}", resultList);
    }
    static void collectionCompare(EntityManager em) {
        List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE m.orders IS NOT EMPTY"
                        , Member.class)
                .getResultList();
        for (Member member : resultList) {
            log.info("member = {}", member);
        }
    }

    static void subQuery4(EntityManager em) {
        List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE m.team = ANY (SELECT t FROM Team t)", Member.class)
                .getResultList();
        for (Member member : resultList) {
            log.info("member = {}",member);
        }
    }
    static void subQuery3(EntityManager em) {
        List<Order> resultList = em.createQuery("SELECT o FROM Order o WHERE o.orderAmount > ALL (SELECT p.stockAmount FROM Product p)", Order.class).getResultList();
        log.info("resultList.size = {}",resultList.size());
        for (Order order : resultList) {
            log.info("order = {}", order);
        }
    }
    static void subQuery2(EntityManager em) {
        List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE NOT EXISTS (SELECT t FROM m.team t WHERE t.name = '팀B')", Member.class)
                .getResultList();
        for (Member member : resultList) {
            log.info("member = {}",member);
        }
    }
    static void subQuery(EntityManager em) {
        List<Member> result1 = em.createQuery("SELECT m FROM Member m WHERE m.age > (SELECT AVG (m2.age) FROM Member m2)", Member.class)
                .getResultList();
        log.info("result1.size() = {}",result1.size());

        List<Member> result2 = em.createQuery("SELECT m FROM Member m WHERE (SELECT COUNT(o) FROM Order o WHERE m = o.member) > 0", Member.class).getResultList();
        List<Member> result3 = em.createQuery("SELECT m FROM Member m WHERE SIZE(m.orders) > 0 ", Member.class).getResultList();
        System.out.println(result2.getFirst().getName());
        log.info("result2.size() = {}", result2.size());
        System.out.println(result3.getFirst().getName());
        log.info("result3.size() = {}", result2.size());
    }
    static void innerJoin(EntityManager em) {
        List<Team> resultList = em.createQuery("SELECT t FROM Team t JOIN t.members WHERE t.name = '팀A'"
                        , Team.class)
                .getResultList();

        List<Integer> resultList1 = em.createQuery("SELECT SIZE(t.members) FROM Team t", Integer.class).getResultList();
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
        Team teamA = new Team();
        teamA.setName("팀A");
        Team teamB = new Team();
        teamB.setName("팀B");
        Member member1 = new Member();
        member1.setAge(27);
        member1.setName("junghwan");
        member1.setTeam(teamA);

        Member member2 = new Member();
        member2.setAge(28);
        member2.setName("ABC");
        member2.setTeam(teamA);

        Member member3 = new Member();
        member3.setAge(29);
        member3.setName("홍길동");
        member3.setTeam(teamB);

        Order order = Order.builder()
                .member(member3)
                .build();

        em.persist(teamA);
        em.persist(teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(order);
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
