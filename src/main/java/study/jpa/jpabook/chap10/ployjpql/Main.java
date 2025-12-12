package study.jpa.jpabook.chap10.ployjpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.Iterators;

import java.util.Iterator;
import java.util.List;

@Slf4j
public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Book book = new Book();
        book.setAuthor("최정환");
        Movie movie = new Movie();
        movie.setName("개발인생");
        em.persist(book);
        em.persist(movie);
        tr.commit();

//        List<Item> selectIFromItemI = em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
//        for (Item item : selectIFromItemI) {
//            log.info("item = {}",item);
//        }
        List<Item> resultList = em.createQuery("SELECT i FROM Item i WHERE TYPE(i) IN (Book, Movie)", Item.class).getResultList();
        for (Item item : resultList) {
            log.info("item = {}",item);
            log.info("item.getId = {}",item.getId());
        }

        List resultList1 = em.createQuery("SELECT i FROM Item i WHERE TREAT (i AS Book ).author = '최정환'").getResultList();

    }


}
