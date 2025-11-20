//package study.jpa.jpabook.model.entity;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
//public class Main {
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//
//        Member member = new Member();
//        Order order = new Order();
//        Order orderw1 = em.find(Order.class, order.getOrderId());
//        Member member1 = orderw1.getMember();
//
//    }
//}
