package study.jpa.jpabook.chap6.manytomany.onetwoway2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        save(em);
        tr.commit();
        find(em);
    }

    static void save(EntityManager em) {
        // 회원 저장
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        em.persist(member1);
        // 상품 저장
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        Order order = new Order();
        order.setMember(member1);
        order.setProduct(productA);
        order.setOrderAmount(2L);
        em.persist(order);
    }

    static void find(EntityManager em) {
        Order order = em.find(Order.class, 1L);

        Member member = order.getMember();
        Product product = order.getProduct();
        log.info("member = {}", member);
        log.info("product = {}", product);
        log.info("order = {}",order);
    }
}
