//package study.jpa.jpabook.chap6.manytomany.onetwoway;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//import lombok.extern.slf4j.Slf4j;
//
//import java.time.LocalDateTime;
//
//@Slf4j
//public class Main {
//
//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//    public static void main(String[] args) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        save(em);
//        tr.commit();
//        find(em);
//    }
//
//    static void save(EntityManager em) {
//
//        // 회원 저장
//        study.jpa.jpabook.chap6.manytomany.onetwoway2.Member member1 = new study.jpa.jpabook.chap6.manytomany.onetwoway2.Member();
//        member1.setId("member1");
//        member1.setUsername("회원1");
//        em.persist(member1);
//
//        // 상품 저장
//        Product productA = new Product();
//        productA.setId("productA");
//        productA.setName("상품1");
//        em.persist(productA);
//
//        MemberProduct memberProduct = new MemberProduct();
//        memberProduct.setMember(member1);
//        memberProduct.setProduct(productA);
//        memberProduct.setOrderAmount(2);
//        memberProduct.setOrderdAt(LocalDateTime.now());
//        em.persist(memberProduct);
//    }
//
//    static void find(EntityManager em) {
//        MemberProductId memberProductId = new MemberProductId();
//        memberProductId.setMember("member1");
//        memberProductId.setProduct("productA");
//
//        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
//
//        Member member = memberProduct.getMember();
//        Product product = memberProduct.getProduct();
//        log.info("member = {} ", member.getUsername());
//        log.info("product = {} ", product.getName());
//        log.info("orderAmount = {}, orderdAt = {}", memberProduct.getOrderAmount(), memberProduct.getOrderdAt());
//    }
//}
