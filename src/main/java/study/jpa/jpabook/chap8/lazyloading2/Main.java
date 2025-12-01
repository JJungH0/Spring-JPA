package study.jpa.jpabook.chap8.lazyloading2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
//        find(emf.createEntityManager());
        findOrders(emf.createEntityManager());
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member = new Member();
        Team team = new Team();
        Order order = new Order();
//        Product product = new Product();
//        order.setProduct(product);
        member.setTeam(team);
        member.getOrders().add(order);
        order.setMember(member);
//        em.persist(product);
        em.persist(order);
        em.persist(team);
        em.persist(member);
        log.info("{}",member.getOrders().size());
        tr.commit();
    }

    static void find(EntityManager em) {
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
        em.find(Member.class, 1L);

    }

    static void findOrders(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        List<Order> orders = member.getOrders();
        log.info("{}",orders.size());
        log.info("orders.getClass().getName() = {}",orders.getClass().getName());
        orders.getFirst();
    }
}
