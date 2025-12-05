package study.jpa.jpabook.chap10.jpql.dto;

import lombok.ToString;

@ToString
public class UserDTO {
    private String name;
    private Integer age;

    public UserDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
