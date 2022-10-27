package com.dodo.bootawsproject.config;

import com.dodo.bootawsproject.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebCofig implements WebMvcConfigurer { // 등록한 핸들러를 스프링에서 인식되도록 config 설정.

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) { // 핸들러 추가.
        resolvers.add(loginUserArgumentResolver);
    }
}
