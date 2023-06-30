package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// JPA를 사용하는 구나. 관리측면
@Entity
// 아래 annotation을 추가하면 USER 테이블에 저장. 생략시 class name을 찾아서 저장.
//@Table(name = "USER")
public class Member {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
