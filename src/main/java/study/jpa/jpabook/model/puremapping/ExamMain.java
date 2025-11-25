//package study.jpa.jpabook.model.puremapping;
//
//import jakarta.persistence.*;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
//@Slf4j
//public class ExamMain {
//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//
//    public static void main(String[] args) {
//        testSave();
////        queryLogicJoin();
////        updateRelation(emf.createEntityManager());
////        deleteRelation(emf.createEntityManager());
////        biDirection(emf.createEntityManager());
//    }
//
//    public static void testSave() {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction et = em.getTransaction();
//
//        Team team1 = new Team("team1", "팀1");
//        Member member1 = new Member("member1", "회원1");
//        member1.setTeam(team1);
//        Member member2 = new Member("member2", "회원2");
//        member2.setTeam(team1);
//
//        et.begin();
//
//        em.persist(team1);
//        em.persist(member1);
//        em.persist(member2);
//        et.commit();
//    }
//
//    public static void queryLogicJoin() {
//        EntityManager em = emf.createEntityManager();
//        String jpql = "select m from Member m JOIN m.team t where t.name = :teamName";
//        List<Member> memberList = em.createQuery(jpql, Member.class)
//                .setParameter("teamName", "팀1")
//                .getResultList();
//
//        for (Member member : memberList) {
//            log.info("[query] member.userName = {}",member.getUsername());
//        }
//    }
//
//    private static void updateRelation(EntityManager em) {
//        log.info("updateRelation 메소드 실행");
//        // 새로운 팀
//        EntityTransaction tr = em.getTransaction();
//
//        Team team2 = new Team("team2", "팀2");
//        tr.begin();
//        em.persist(team2);
//
//        Member member = em.find(Member.class, "member1");
//        member.setTeam(team2);
//        tr.commit();
//    }
//
//    private static void deleteRelation(EntityManager em) {
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        Member member1 = em.find(Member.class, "member1");
//        log.info("[Member1.team] = {}", member1.getTeam());
//        member1.setTeam(null);
//        log.info("연관 관계 제거후 [Member1.team] = {}", member1.getTeam());
////        em.remove(member1.getTeam());
//        tr.commit();
//    }
//
//    private static void biDirection(EntityManager em) {
//        Team team = em.find(Team.class, "team1");
//        List<Member> members = team.getMembers();
//        for (Member member : members) {
//            log.info("member.userName = {}", member.getUsername());
//        }
//
//    }
//}
