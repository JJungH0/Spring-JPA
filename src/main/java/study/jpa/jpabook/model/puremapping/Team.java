//package study.jpa.jpabook.model.puremapping;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@NoArgsConstructor
//@Getter @Setter
//@Entity
//public class Team {
//
//    public Team(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    @Id
//    @Column(name = "team_id")
//    private String id;
//    @Column(name = "team_name")
//    private String name;
//
//    /**
//     * 양방향 연관관계 매핑
//     * mappedBy :
//     * -> 반대쪽 매핑 필드의 이름값 (= 즉 외래키 주인은 Member쪽)
//     */
//    @OneToMany(mappedBy = "team")
//    private List<Member> members = new ArrayList<>();
//}
