package study.jpa.jpabook.chap6.manytomany.twoway;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Test {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        save(em);
        findInverse(em);
        tr.commit();
    }

    static void save(EntityManager em) {
        Product product = new Product();
        product.setProductId("productA");
        product.setName("ProJungHwan");
        em.persist(product);

        Member member = new Member();
        member.setMemberId("memberA");
        member.setUsername("JungHwan");
        member.addProduct(product);
        em.persist(member);
    }

    static void findInverse(EntityManager em) {
        Product product = em.find(Product.class, "productA");
        List<Member> members = product.getMembers();
        for (Member member : members) {
            log.info("member.getMemberId = {}",member.getMemberId());
            log.info("member.getUsername = {}",member.getUsername());
        }
    }
}
