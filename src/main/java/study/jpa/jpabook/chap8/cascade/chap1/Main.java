package study.jpa.jpabook.chap8.cascade.chap1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        saveNoCascade1(emf.createEntityManager());
        orphanRemove(emf.createEntityManager());
//        removeCascade(emf.createEntityManager());
//        saveNoCascade2(emf.createEntityManager());
    }

    private static void saveNoCascade1(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Parent parent = new Parent();

        Child child1 = new Child();
        Child child2 = new Child();

        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        child1.setParent(parent);
        child2.setParent(parent);

        em.persist(parent);
        em.persist(child1);
        em.persist(child2);
        tr.commit();
    }

    private static void saveNoCascade2(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Parent parent = new Parent();
        em.persist(parent);

        Child child1 = new Child();
        child1.setParent(parent);
        parent.getChildren().add(child1);
        em.persist(child1);

        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);
        em.persist(child2);
        tr.commit();
    }
    static void removeCascade(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Parent parent = em.find(Parent.class, 1L);
        em.remove(parent);
        tr.commit();
    }

    static void orphanRemove(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Parent parent = em.find(Parent.class, 1L);
//        parent.getChildren().remove(0);
        parent.getChildren().clear();
        tr.commit();
    }
}
