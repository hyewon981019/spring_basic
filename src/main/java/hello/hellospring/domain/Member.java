package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa에서는 엔티티 개념
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //아이디는 사용자가 넣는게아니라 얘가 생성
    private Long id;

    //@Column(name = "username") 디비의 username과 이 변수가 매핑됨
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
