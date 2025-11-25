//package study.jpa.jpabook.start;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Slf4j
//public class ExamMergeMain{
//
//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//
//    public static void main(String[] args) {
//        Member member = createMember("memberA", "Junghwan");
//
//        // 현재 member는 준영속 상태 (= update 쿼리발생 X)
//        member.setUsername("회원명변경");
//
//        mergeMember(member);
//
//        /**
//         * 비영속 상태 병합 :
//         */
//        Member testMember = new Member();
//        testMember.setId("junghwan");
//        testMember.setUsername("최정환");
//        testMember.setAge(27);
//
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        Member mergeMember = em.merge(testMember);
//        logic(em);
//        tr.commit();
//
//
//    }
//
//    static Member createMember(String memberId, String name) {
//        // 영속성 컨텍스트 1 시작
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tr = em.getTransaction();
//        // 트랜잭션 시작
//        tr.begin();
//        Member member = new Member();
//        member.setId(memberId);
//        member.setUsername(name);
//
//        // 영속화 (= InsertQuery 저장)
//        em.persist(member);
//
//        // 트랜잭션 종료 (= 커밋)
//        tr.commit();
//
//        // 영속성 컨텍스트 1 종료
//        // 이떄 반환된 멤버 객체는 준영속 상태가 됨
//        em.close();
//
//        return member;
//    }
//
//    static void mergeMember(Member member) {
//        // 영속성 컨텍스트 2 시작
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tr = em.getTransaction();
//
//        // 트랜잭션 시작
//        tr.begin();
//
//        /**
//         * 이제 여기서 인자로 넘어온 member는 필요가 없으니
//         * 준영속 엔티티를 참조하던 변수를 영속 엔티티를 참조하도록 변경
//         * member = em.merge(member);
//         */
//
//        Member mergeMember = em.merge(member);
//
//        tr.commit();
//
//        // 준영속 상태 (= 인자로 넘겨받은 Member객체)
//        log.info("member = {}", member.getUsername());
//
//        // 영속 상태 (= merge로 병합한 상태)
//        log.info("mergeMember = {}", mergeMember);
//
//        // 당연히 영속성 컨텍스트에 존재 X
//        log.info("em contains member = {}",em.contains(member));
//
//        // 병합되었으니 존재 O
//        log.info("em contains mergeMember = {}",em.contains(mergeMember));
//
//        // 영속성 컨텍스트 닫기 (= 종료)
//        em.close();
//    }
//
//    private static void logic(EntityManager em) {
//        Board board = new Board();
//        em.persist(board);
//        log.info("board.id = {}", board.getId());
//    }
//}
