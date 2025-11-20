//package study.jpa.jpabook.start;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//
//@Entity
//@TableGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "BOARD_SEQ", allocationSize = 1)
//public class Board2 {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE,
//            generator = "BOARD_SEQ_GENERATOR")
//    private Long id;
//}
