package study.jpa.jpabook.chap6.manytomany.oneway;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Test {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
        find(emf.createEntityManager());

    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Product productA = new Product();
        productA.setProductId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setMemberId("member1");
        member1.setUsername("회원1");
        member1.getProducts().add(productA); // 연관관계 설정
        em.persist(member1);
        tr.commit();
    }

    static void find(EntityManager em) {
        Member member = em.find(Member.class, "member1");
        List<Product> products = member.getProducts();
        for (Product product : products) {
            System.out.println("product.name = " + product.getName());

        }
    }
}
