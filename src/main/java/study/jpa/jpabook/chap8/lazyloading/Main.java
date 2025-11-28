package study.jpa.jpabook.chap8.lazyloading;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
//        getMemberAndTeam(emf.createEntityManager());
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Team team = new Team();
        team.setName("ILLO");

        Member member = new Member();
        member.setName("JungHwan");
        member.setTeam(team);
        em.persist(team);
        em.persist(member);
        tr.commit();
    }

    static void getMemberAndTeam(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        log.info(member.getClass().getName());
        Team team = member.getTeam();
        log.info(team.getName());
    }
}
