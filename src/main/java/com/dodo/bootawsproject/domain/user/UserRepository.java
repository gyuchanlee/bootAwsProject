package com.dodo.bootawsproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    // 소셜 로그인으로 반환 된 값 중, email로 생성된 이용자인지, 처음 가입자인지 판단하기 위한 메서드.
    Optional<Users> findByEmail(String email);
}
