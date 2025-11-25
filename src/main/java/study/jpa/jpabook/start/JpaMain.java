//package study.jpa.jpabook.start;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
//@Slf4j
//public class JpaMain {
//
//    public static void main(String[] args) {
////        [엔티티 매니저 팩토리] - 생성
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
////        [엔티티 매니저] - 생성
//        EntityManager em = emf.createEntityManager();
////        [트랜잭션] - 획득
//        EntityTransaction tx = em.getTransaction();
//
//        try {
//            tx.begin(); // - [트랜잭션] 시작
//            logic(em); // - 비즈니스 로직 실행
//            tx.commit(); // - [트랜잭션] 커밋
//        } catch (Exception e) {
//            tx.rollback(); // [트랜잭션] 롤백
//        } finally {
//            em.close(); // [엔티티 매니저] - 종료
//            emf.close(); // [엔티티 매니저 팩토리] - 종료
//        }
//    }
//
//    private static void logic(EntityManager em) {
//        String id = "id1";
//        Member member = new Member();
//        member.setId(id);
//        member.setUsername("정환");
//        member.setAge(2);
//
//        // 해당 엔티티를 영속성 컨텍스트 (= 1차 캐시)에 등록하고, JPA가 관리상태로 만듬)
//        /**
//         * - 해당 엔티티를 영속성 컨텍스트로 등록 (= 1차 캐시)
//         * - Hibernate 내부에서 액션 큐에 insert 작업을 추가
//         * - 동시에 스냅샷 (= 원본 상태)를 찍어서 기록
//         */
//        em.persist(member);
//
//        /**
//         * - 아직 insert/update 쿼리 X
//         * - 하지만 엔티티 상태는 변경
//         */
//        // 수정
//        member.setAge(20);
//
//
//        /**
//         * - 영속성 컨텍스트 (= 1차 캐시)에서 값을 바로 꺼내옴, DB까지 접근 X)
//         */
//        // 한 건 조회
//        Member findMember = em.find(Member.class, id);
//        log.info("findMember = {}", findMember);
//
//        // 1차 캐시 동일성 테스트
//        Member findMember2 = em.find(Member.class, "id1");
//        log.info("result = {}", findMember == findMember2);
//        /**
//         * JPQL 실행 :
//         * - flush()를 먼저 수행
//         *  - 액션 큐에 쌓인 INSERT 실행
//         *  - 변경 감지 (= Dirty Checking) 실행
//         *      - 스냅샷 (= 처음 상태) || 현재 엔티티 상태 비교
//         *      - age: 2 -> 20으로 바뀐 걸 포착
//         *      - 해당 엔티티 수정됐다라고 판단 후 - Update 쿼리 추가
//         * - flush에 쌓인 insert/update 순서로 쿼리 실행
//         * - 그후 JPQL에 해당하는 select ... from MEMBER 실행
//         */
//        // 목록 조회
//        List<Member> members =
//                em.createQuery("select m from Member m", Member.class).getResultList();
//        log.info("members.size = {}",members.size());
//
//        // 삭제
//        em.remove(member);
//    }
//}
