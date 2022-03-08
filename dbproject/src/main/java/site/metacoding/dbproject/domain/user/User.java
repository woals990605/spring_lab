package site.metacoding.dbproject.domain.user;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// model과 repositroy를 구분하는건 옜날모델
// 지금은 domain으로 만듬
//JPA 라이브러리는 Java(자바 언어)로 Persistenece(영구적인 저장)을 제공하는 API(노출되어 있는 메서드)
//1. CRUD 메서드를 기본 제공
//2. 자바코드로 DB를 자동 생성 기능 제공
//3. ORM 제공!! 지금 몰라도 됨 

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // 서버 실행시 해당 클래스로 테이블을 생성하는 어노테이션
@EntityListeners(AuditingEntityListener.class) // 현재시간 입력을 위해 필요한 어노테이션
public class User {
    // IDENTITY 전략은 DB에 번호증가 전략을 위임하는 것!! - 알아서 디비에 맞게 해줌.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // primary key

    @Column(length = 20, unique = true) // VARCHAR(20) 길이설정
    private String username;// ssar 아이디

    @Column(length = 12, nullable = false)
    private String password;

    // VARCHAR의 크기를 벗어나는 length를 넣으면 알아서 longtext타입으로 바뀐다.
    @Column(length = 16000000, nullable = false)
    private String email;

    // 시간 -> DB는 LocalDateTime타입이 없으니까 알아서 datetime타입으로 바뀜
    // 자바에선 커멜표기법인데 디비는 언더바가 디폴트임
    // 내가 설정한 이름 그대로 설정되게 하는 기능이 있음
    @CreatedDate // insert
    private LocalDateTime createDate;
    @LastModifiedDate // insert, update
    private LocalDateTime updateDate;

    /////////////////////////////////////////// DB테이블과 상관없음
    @Transient
    private String remember;
}