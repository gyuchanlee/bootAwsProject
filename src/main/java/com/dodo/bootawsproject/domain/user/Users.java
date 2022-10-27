package com.dodo.bootawsproject.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private  String picture;

    @Enumerated(EnumType.STRING) // Enum을 어떤 형태로 저장할지 지정(기본값 int) > int는 값이 어떤 코드를 의미하는 지 DB에서 볼 때 알기 힘듬.
    @Column(nullable = false)
    private Role role; // 권한 관리하는 Role 클래스 따로 생성

    @Builder
    public Users(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Users update(String name, String picture) { // update 구문은 Entity에서 관리
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
