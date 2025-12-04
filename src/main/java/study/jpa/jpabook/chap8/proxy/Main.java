package study.jpa.jpabook.chap8.proxy;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

@Slf4j
public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {

        save(emf.createEntityManager());
//        printUser(emf.createEntityManager());
        printUserAndTeam(emf.createEntityManager());


    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Team team = new Team();
        team.setTeamName("1Team");

        Member member = new Member();
        member.setUserName("JungHwan");
        member.setTeam(team);
        em.persist(team);
        em.persist(member);
        tr.commit();
    }
    static void printUserAndTeam(EntityManager em) {

        Member member = em.find(Member.class, 1L);

        Team team = member.getTeam();


        System.out.println(team);
//        log.info("team = {}", team);
//        log.info("team2 = {}",team.getClass().getName());
    }

    static void printUser(EntityManager em) {
        log.info("printUser 출력");
        Member member = em.getReference(Member.class, 1L);
//        Member member = em.find(Member.class, 1L);
        log.info("member = {}", member);
        boolean loaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
        log.info("loaded = {}", loaded);
        log.info("memberProxy = {} ",member.getClass().getName());

    }


}
