//package study.jpa.jpabook.model.puremapping;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Objects;
//
//@NoArgsConstructor
//@Getter
//@Entity
//public class Member {
//
//    public Member(String id, String username) {
//        this.id = id;
//        this.username = username;
//    }
//
//    @Id
//    @Column(name = "member_id")
//    private String id;
//
//    @Column(name = "member_name")
//    private String username;
//
//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;
//
//    public void setTeam(Team team) {
//        // 기존 팀과 연관관계 제거
//        if (Objects.nonNull(this.team)) {
//            this.team.getMembers().remove(this);
//        }
//        this.team = team;
//        team.getMembers().add(this);
//    }
//}
