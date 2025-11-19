package study.jpa.jpabook.start;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Data :
 * Getter, Setter
 * RequiredArgsConstructor
 * ToString, EqualsAndHashCode를 포함한다.
 */
@Data
@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME","AGE"}
)})
public class Member {
    @Id
    @Column(name = "id")
    private String id;

    // 제약 조건 추가
    @Column(name = "name", nullable = false, length = 10)
    private String username;

    private Integer age;

    // 회원 권한 (= enum값을 사용 시 @Enumerated 어노테이션 사용)
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

    // 생성 시각 (= Java 날짜 타입 매핑은 @Temporal를 사용, 참고로 이거 jakarta로 해야함)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    // 수정 시각
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    // 소개글 글자 수 제한 X
    @Lob // (DB의 VARCHAR()를 사용못하니 CLOB,BLOB 타입으로 지정해야함)
    private String description;
}
