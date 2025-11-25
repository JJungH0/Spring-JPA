package study.jpa.jpabook.chap7.composite1.idclass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        save(emf.createEntityManager());
        find(emf.createEntityManager());
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Parent parent = new Parent("myId1", "myId2", "Junghwan");
        em.persist(parent);
        tr.commit();
    }

    static void find(EntityManager em) {
        ParentId parentId = new ParentId("myId1", "myId2");
        System.out.println(em.find(Parent.class, parentId));
    }
}
