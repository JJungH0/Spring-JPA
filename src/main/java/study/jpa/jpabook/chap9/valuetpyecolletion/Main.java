package study.jpa.jpabook.chap9.valuetpyecolletion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        save(emf.createEntityManager());
        update(emf.createEntityManager());
        find(emf.createEntityManager());
    }

    static void save(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member = new Member();

        // 임베디드 값 타입
        member.setHomeAddress(new Address("통영", "몽돌해수욕장", "660-123"));

        // 기본값 타입 컬렉션
        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        // 임베디드 값 타입 컬렉션
        member.getAddressHistory().add(new Address("서울", "강남", "123-123"));
        member.getAddressHistory().add(new Address("서울", "강북", "000-000"));
        em.persist(member);
        tr.commit();
    }

    static void update(EntityManager em) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Member member = em.find(Member.class, 1L); // sql 1회 발생

        member.setHomeAddress(new Address("새로운도시", "신도시1", "12345")); // 더티 체킹으로 UPDATE 쿼리 쓰기지연 저장소에 적립

        Set<String> favoriteFoods = member.getFavoriteFoods();
        favoriteFoods.remove("탕수육"); // SELECT로 조회 후 DELETE 쿼리 쓰기 지연
        favoriteFoods.add("치킨"); // INSERT 쓰기 지연

        List<Address> addressHistory = member.getAddressHistory();
        addressHistory.remove(new Address("서울", "강북", "000-000"));
        addressHistory.add(new Address("새로운도시", "새로운주소", "123-456"));
        tr.commit();
    }

    static void find(EntityManager em) {
        Member member = em.find(Member.class, 1L);

        Address homeAddress = member.getHomeAddress();

        Set<String> favoriteFoods = member.getFavoriteFoods();

        for (String favoriteFood : favoriteFoods) {
            log.info("favoriteFood = {}",favoriteFood);
        }

        List<Address> addressHistory = member.getAddressHistory();
        log.info("{}",addressHistory.get(0));

        log.info("member = {}", member);
    }
}
