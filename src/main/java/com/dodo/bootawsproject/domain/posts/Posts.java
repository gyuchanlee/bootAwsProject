package com.dodo.bootawsproject.domain.posts;

import com.dodo.bootawsproject.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // Entity에서는 절대 setter를 쓰지 않음!! > 쓸 일이 있다면 목적과 의도가 명확한 메서드로 수정.
@NoArgsConstructor
@Entity // 테이블과 링크 될 클래스임을 선언 : 기본값으로 카멜케이스 이름을 언더스코어 이름으로 변경.
public class Posts extends BaseTimeEntity { // 등록/수정 날짜 관리 엔티티 상속.
    @Id // pk값 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 기능을 하도록 설정
    private Long id;
    // 웬만하면 PK값은 엔티티에서 Long 타입에 auto_increment 추천 : 유니크키(주민등록번호 등)을 쓰면 변경 시, 수정이 힘듬. 복합키 또한 테이블 fk연결 때 문제될 수 있음.

    @Column(length = 500, nullable = false) // 컬럼 설정을 위해 선언 : 문자열 길이 500, not null 선언.
    private String title;

    @Column(columnDefinition = "text", nullable = false) // 컬럼 타입을 text로 변경.
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) { // title, content 컬럼 Update
        this.title = title;
        this.content = content;
    }
}
