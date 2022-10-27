package com.dodo.bootawsproject.config.auth;

import com.dodo.bootawsproject.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 컨트롤러 메서드의 특정 파라미터를 지원하는 지 판단.
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; // @LoginUser이 붙어 있는 가?
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); // 파라미터 클래스 타입이 SessionUser.class 인가?
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        // 파라미터에 전달할 객체를 생성
        // 이 클래스에서는 세션에서 객체를 가져와 반환.
        return httpSession.getAttribute("user");
    }
}
