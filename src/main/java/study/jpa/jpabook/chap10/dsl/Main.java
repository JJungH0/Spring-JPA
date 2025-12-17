package study.jpa.jpabook.chap10.dsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryModifiers;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.JpaSubQuery;
import org.springframework.util.StringUtils;
import study.jpa.jpabook.chap10.jpql.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class Main {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    static final JPAQueryFactory query = new JPAQueryFactory(emf.createEntityManager());
    public static void main(String[] args) {
        save(emf.createEntityManager());
//        queryDsl(emf.createEntityManager());
//        searchQuery(emf.createEntityManager());
//        pageable(emf.createEntityManager());
//        groupDsl(emf.createEntityManager());
//        join1(emf.createEntityManager());
//        setterJoin(emf.createEntityManager());
//        subQuery(emf.createEntityManager());
//        subQuery2(emf.createEntityManager());
//        projectionQuery(emf.createEntityManager());
//        projectionQuery2(emf.createEntityManager());
//        beanQuery(emf.createEntityManager());
//        filedQuery();
//        constructorQuery();
//        batchQuery(emf.createEntityManager());
//        constructorQuery();
//        batchQuery2(emf.createEntityManager());

        dynamicQuery();
        constructorQuery();
    }

    static void dynamicQuery() {
        SearchParam param = new SearchParam();
        param.setName("백엔드 개발취준");
        param.setPrice(2000000);

        QProduct product = QProduct.product;

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(param.getName())) {
            builder.and(product.name.contains(param.getName()));
        }
        if (param.getPrice() != 0) {
            builder.and(product.price.gt(param.getPrice()));
        }

        query.selectFrom(product)
                .where(builder)
                .fetch();
    }
    static void batchQuery2(EntityManager em) {
        QProduct product = QProduct.product;
        JPADeleteClause jpaDeleteClause = new JPADeleteClause(em, product);

        EntityTransaction tr = em.getTransaction();
        tr.begin();
        jpaDeleteClause.where(product.name.eq("수정된 사과박스"))
                .execute();
        tr.commit();
    }
    static void batchQuery(EntityManager em) {
        QProduct product = QProduct.product;
        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, product);

        EntityTransaction tr = em.getTransaction();
        tr.begin();
        jpaUpdateClause.where(product.name.eq("사과박스"))
                .set(product.name, "수정된 사과박스")
                .execute();
        em.clear();
        tr.commit();
    }
    static void beanQuery(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct product = QProduct.product;

        List<ProductDTO> fetch = query.select(Projections.bean(
                        ProductDTO.class,
                        product.name.as("username"),
                        product.price))
                .from(product)
                .fetch();

        for (ProductDTO productDTO : fetch) {
            log.info("productDTO = {}",productDTO);
        }
    }

    static void constructorQuery() {
        QProduct product = QProduct.product;
        List<ProductDTO> result = query.select(Projections.constructor(
                ProductDTO.class,
                product.name,
                product.price
        )).from(product).fetch();

        for (ProductDTO productDTO : result) {
            log.info("productDTO = {}",productDTO);
        }

    }

    static void filedQuery() {
        QProduct product = QProduct.product;

        List<ProductDTO> result = query.select(
                        Projections.fields(
                                ProductDTO.class,
                                product.name.as("username"),
                                product.price))
                .from(product)
                .fetch();

        for (ProductDTO productDTO : result) {
            log.info("productDTO = {}",productDTO);
        }


    }
    static void projectionQuery2(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember member = QMember.member;

        List<Tuple> result = query.select(member.name, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            log.info("name = {}",tuple.get(member.name));
            log.info("age = {}",tuple.get(member.age));
        }
    }

    static void projectionQuery(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember member = QMember.member;
        List<String> result = query.select(member.name)
                .from(member)
                .fetch();

        for (String s : result) {
            log.info("s = {}",s);
        }
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
        member2.setName("회원2");
        member2.setAge(28);

        Product product1 = new Product();
        product1.setPrice(BigDecimal.valueOf(20000));
        product1.setName("사과박스");
        product1.setStockAmount(200);

        Product product2 = new Product();
        product2.setPrice(BigDecimal.valueOf(20000));
        product2.setName("감귤박스");
        product2.setStockAmount(200);

        Product product3 = new Product();
        product3.setPrice(BigDecimal.valueOf(30000));
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
