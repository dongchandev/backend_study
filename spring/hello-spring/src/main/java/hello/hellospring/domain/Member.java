package hello.hellospring.domain;

import jakarta.persistence.*;
import jdk.jfr.Name;

//jpa 사용 위해 엔티티를 매핑해야 함
//jpa는 인터페이스
//구현체: hibernate 등
//ORM:Object Relational Mapping(객체-관계-매핑)
@Entity
public class Member {
    //IDENTITY : id 값을 세팅하지 않고, insert 쿼리를 날리면 database 시스템에서 자동으로 번호를 받음
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
