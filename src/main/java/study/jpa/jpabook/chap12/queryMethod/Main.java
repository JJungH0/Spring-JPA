package study.jpa.jpabook.chap12.queryMethod;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import study.jpa.jpabook.chap12.queryMethod.dto.entity.Member;
import study.jpa.jpabook.chap12.queryMethod.repo.MemberRepository;

@Slf4j
public class Main {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        save(emf.createEntityManager());
        bulkAgeUp(emf.createEntityManager());
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member = new Member();
        member.setName("junghwan");
        member.setAge(27);
        member.setEmail("junghwan__@naver.com");
        em.persist(member);
        tr.commit();

    }


    static void bulkAgeUp(EntityManager em) {

        Member member = em.find(Member.class, 1L);
        log.info("1. member = {}",member);

        String qlString = "update Member m set m.age = m.age * 2 where m.name = :name";
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        int result = em.createQuery(qlString)
                .setParameter("name", "junghwan")
                .executeUpdate();
        log.info("result = {}",result);
        tr.commit();
//        em.clear();
//        Member member2 = em.find(Member.class, 1L);
        Member singleResult = em.createQuery("SELECT m FROM Member m WHERE m.name = 'junghwan'"
                , Member.class).getSingleResult();
        log.info("2. member = {}", singleResult);
    }
}
