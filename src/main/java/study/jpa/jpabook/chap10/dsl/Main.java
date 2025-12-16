package study.jpa.jpabook.chap10.dsl;

import com.querydsl.core.QueryModifiers;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.JpaSubQuery;
import study.jpa.jpabook.chap10.jpql.*;

import java.util.List;

@Slf4j
public class Main {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
//        queryDsl(emf.createEntityManager());
//        searchQuery(emf.createEntityManager());
//        pageable(emf.createEntityManager());
//        groupDsl(emf.createEntityManager());
//        join1(emf.createEntityManager());
//        setterJoin(emf.createEntityManager());
//        subQuery(emf.createEntityManager());
        subQuery2(emf.createEntityManager());
    }

    static void subQuery(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

        query.selectFrom(product)
                .where(product.price.eq(
                        query.select(productSub.price.max()).from(productSub)
                ))
                .fetchOne();
    }

    static void subQuery2(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;
        QProduct subProduct = new QProduct("subProduct");

        query.selectFrom(product)
                .where(product.in(query.selectFrom(subProduct)
                                .where(product.name.eq(subProduct.name))))
                .fetch();
    }

    static void setterJoin(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        query.select(order, member)
                .from(order, member)
                .where(order.member.eq(member))
                .fetch();

    }

    static void join1(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember member = QMember.member;
        QProduct product = QProduct.product;
        QOrder order = QOrder.order;

        /**
         * 기본 조인 :
         */
        List<Order> fetch = query.select(order)
                .from(order)
                .join(order.member, member)
                .leftJoin(order.product, product)
                .fetch();
        for (Order fetch1 : fetch) {
            log.info("basicJoin = {}",fetch1.getMember());
        }

        /**
         * 조인 On 사용 :
         */
        List<Order> onJoin = query.select(order)
                .from(order)
                .join(order.product, product)
                .groupBy(order.id)
                .having(product.id.count().gt(2))
                .fetch();
        for (Order order1 : onJoin) {
            log.info("onJoin = {}",order1);
        }
        /**
         * 패치 조인 사용 :
         */
        List<Order> fetchJoin = query.select(order)
                .from(order)
                .innerJoin(order.member, member).fetchJoin()
                .leftJoin(order.product, product).fetchJoin()
                .fetch();
        for (Order order1 : fetchJoin) {
            log.info("fetchJoin = {}",order1);
        }
    }

    static void groupDsl(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;

        query.select(product.count())
                .from(product)
                .groupBy(product.price)
                .having(product.price.gt(19999))
                .fetch();


    }

    static void pageable(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;
//        QueryModifiers queryModifiers = new QueryModifiers(20L, 10L);

//        query.select(product)
//                .from(product)
//                .restrict(queryModifiers)
//                .fetch();

        List<Product> products = query.select(product)
                .from(product)
                .where(product.price.gt(20000))
                .orderBy(product.price.desc(), product.stockAmount.asc())
                .offset(10).limit(20)
                .fetch();

        log.info("{}", products.size());
    }

    static void queryDsl(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        List<Member> members = query
                .select(qMember)
                .from(qMember)
                .where(qMember.name.eq("회원1"))
                .orderBy(qMember.name.desc())
                .fetch();

        for (Member member : members) {
            log.info("{}", member);
        }
    }

    static void searchQuery(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;

        query.select(product)
                .from(product)
                .where(product.name.eq("좋은 상품").and(product.price.gt(20000)))
                .fetch();
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member1 = new Member();
        member1.setAge(27);
        member1.setName("회원1");

        Member member2 = new Member();
        member2.setName("회원1");
        member2.setAge(28);

        Product product1 = new Product();
        product1.setPrice(20000);
        product1.setName("사과박스");
        product1.setStockAmount(200);

        Product product2 = new Product();
        product2.setPrice(10000);
        product2.setName("감귤박스");
        product2.setStockAmount(200);

        Product product3 = new Product();
        product3.setPrice(30000);
        product3.setName("바나나박스");
        product3.setStockAmount(200);

        em.persist(member1);
        em.persist(member2);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        tr.commit();
    }
}
