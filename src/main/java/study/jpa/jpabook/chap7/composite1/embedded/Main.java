package study.jpa.jpabook.chap7.composite1.embedded;

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
        find(emf.createEntityManager());

        ParentId parentId1 = new ParentId("a", "b");
        ParentId parentId2 = new ParentId("a", "b");

        log.info("parentId1.equals = {}",parentId1.equals(parentId2));
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();

        Parent parent = new Parent();
        ParentId parentId = new ParentId("myId1", "myId2");
        parent.setId(parentId);
        parent.setName("Junghwan");

        em.persist(parent);

        tr.commit();
    }

    static void find(EntityManager em) {
        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent = em.find(Parent.class, parentId);
        log.info("parent = {}",parent);
    }
}
