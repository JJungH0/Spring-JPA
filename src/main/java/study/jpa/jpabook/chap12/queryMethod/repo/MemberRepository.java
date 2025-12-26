package study.jpa.jpabook.chap12.queryMethod.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import study.jpa.jpabook.chap12.queryMethod.dto.entity.Member;

import java.util.List;

public interface MemberRepository extends Repository<Member,Long> {
    List<Member> findByEmailAndName(String email, String name);

    List<Member> findByMemberName(@Param("memberName") String name);

    @Query("select m from Member m where m.name = :name")
    Member customFindByUserName(String name);

    @Query(value = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ?0", nativeQuery = true)
    Member nativeFindUsername(String name);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.age = m.age * 2 WHERE m.name = :username")
    int bulkAgeUp(@Param("username") String username);

}
