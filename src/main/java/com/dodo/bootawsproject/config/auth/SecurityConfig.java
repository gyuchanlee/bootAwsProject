package com.dodo.bootawsproject.config.auth;

import com.dodo.bootawsproject.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    // 바뀐 방식으로 bean 등록으로 http - SecurityFilterChain 반드시 사용
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console화면을 보기 위해 해당 옵션들을 꺼둠.
                .and()
                .authorizeRequests() // URL별 권환 관리를 설정하는 옵션의 시작점.
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/profile").permitAll() //전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 관리 대상을 지정하는 옵션. url, http 메소드별로 관리 가능. + USER권한만 열람.
                .anyRequest().authenticated() // 설정된 값들 이외 나머지 url를 나타냄 + 인증된 사용자들에게만 허용.
                .and()
                .logout() // 로그아웃 설정 진입
                .logoutSuccessUrl("/") // 로그웃 성공 시, url 리다이렉트
                .and()
                .oauth2Login() // oauth2 로그인 설정 시작점
                .userInfoEndpoint() // oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당.
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시, 후속 조치를 시행할 UserService 인터페이스 구현체를 등록
                // 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능.

        return http.build();
    }
}
