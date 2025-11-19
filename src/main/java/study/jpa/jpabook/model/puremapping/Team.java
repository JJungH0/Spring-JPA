package study.jpa.jpabook.model.puremapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Team {
    @Id
    @Column(name = "team_id")
    private String id;
    @Column(name = "team_name")
    private String name;
}
