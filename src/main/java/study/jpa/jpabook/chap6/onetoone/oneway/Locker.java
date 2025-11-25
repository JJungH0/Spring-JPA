package study.jpa.jpabook.chap6.onetoone.oneway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long lockerId;

    @Column(name = "locker_name")
    private String lockerName;
}
