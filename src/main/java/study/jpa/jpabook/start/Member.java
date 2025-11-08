package study.jpa.jpabook.start;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @Data :
 * Getter, Setter
 * RequiredArgsConstructor
 * ToString, EqualsAndHashCode를 포함한다.
 */
@Data
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;
}
