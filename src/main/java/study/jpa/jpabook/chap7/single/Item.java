package study.jpa.jpabook.chap7.single;

import jakarta.persistence.*;

//@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private String artist;

    private String director;

    private String actor;

    private String author;

    private String isbn;

}
