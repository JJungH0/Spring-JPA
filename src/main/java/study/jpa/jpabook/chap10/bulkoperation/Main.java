package study.jpa.jpabook.chap10.bulkoperation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import study.jpa.jpabook.chap10.jpql.Product;

import java.math.BigDecimal;

@Slf4j
public class Main {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    public static void main(String[] args) {
        save(emf.createEntityManager());
        bulkUpdate(emf.createEntityManager());
        bulkDelete(emf.createEntityManager());
    }

    static void bulkDelete(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        int resultCount = em.createQuery("DELETE FROM Product p " +
                        "WHERE p.stockAmount < :stockAmount")
                .setParameter("stockAmount", 6)
                .executeUpdate();
        tr.commit();
        log.info("삭제된 제품 갯수 -> {}",resultCount);
    }
    static void bulkUpdate(EntityManager em) {
        Product product = em.find(Product.class, 1L);
        log.info("UPDATE 쿼리 실행 전 = {}",product);

        EntityTransaction tr = em.getTransaction();
        tr.begin();
        String qlString = "UPDATE Product p " +
                "SET p.price = p.price * 1.1 " +
                "where p.stockAmount < :stockAmount";

        int resultCount = em.createQuery(qlString)
                .setParameter("stockAmount", 10)
                .executeUpdate();
        tr.commit();
        log.info("변경된 상품 갯수 -> {}",resultCount);

        Product updateProduct = em.find(Product.class, 1L);
        log.info("UPDATE 쿼리 실행 후 = {}", updateProduct);
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName("테스트 상품"+i);
            product.setPrice(BigDecimal.valueOf(20000 + i * 1000));
            product.setStockAmount(1 + i);
            em.persist(product);
        }
        tr.commit();
    }
}
