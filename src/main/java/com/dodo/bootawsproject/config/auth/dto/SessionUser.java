package com.dodo.bootawsproject.config.auth.dto;

import com.dodo.bootawsproject.domain.user.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // 인증된 사용자 정보만 필요. > 직렬화 기능을 가진 세션 Dto

    private String name;
    private String email;
    private String picture;

    public SessionUser(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
