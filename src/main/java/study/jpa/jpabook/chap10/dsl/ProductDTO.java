package study.jpa.jpabook.chap10.dsl;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class ProductDTO {
    private String username;
    private int price;
}
