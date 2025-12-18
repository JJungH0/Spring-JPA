package study.jpa.jpabook.chap10.context;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import study.jpa.jpabook.chap10.jpql.Member;
import study.jpa.jpabook.chap10.jpql.Product;

import java.math.BigDecimal;

@Slf4j
public class Main {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        save(emf.createEntityManager());
//        find(emf.createEntityManager());
//        jpql(emf.createEntityManager());
        flushModeJpql(emf.createEntityManager());
    }
    static void flushModeJpql(EntityManager em) {
        em.setFlushMode(FlushModeType.COMMIT);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Product product = em.find(Product.class, 1L);
        product.setPrice(BigDecimal.valueOf(20000));
//        em.flush();

        Product resultProduct = em.createQuery("SELECT p FROM Product p where p.price = :price", Product.class)
                .setParameter("price", 20000)
                .getSingleResult();
        tr.commit();

        log.info("product = {}", product);
        log.info("resultProduct = {}",resultProduct);
        log.info("product == resultProduct = {}",product == resultProduct);
    }

    static void jpql(EntityManager em) {
        Member member1 = em.createQuery("SELECT m FROM Member m WHERE m.id = :id", Member.class)
                .setParameter("id", 1L)
                .getSingleResult();

        Member member2 = em.createQuery("SELECT m FROM Member m WHERE m.id = :id", Member.class)
                .setParameter("id", 1L)
                .getSingleResult();

        log.info("member1 == member2 ={}",member1==member2);
    }
    static void find(EntityManager em) {
        Member member1 = em.find(Member.class, 1L);
        Member member2 = em.find(Member.class, 1L);
        log.info("member1 == member2 = {}",member1 == member2);
    }
    static void save(EntityManager em) {
        Member member = new Member();
        member.setName("테스터1");
        member.setAge(27);
        Product product = new Product();
        product.setName("테스트 상품");
        product.setPrice(BigDecimal.valueOf(10000));
        product.setStockAmount(10);

        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(member);
        em.persist(product);
        tr.commit();
    }
}
