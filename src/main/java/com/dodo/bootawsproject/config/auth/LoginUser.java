package com.dodo.bootawsproject.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치 설정 (메소드의 매개값 지정), TYPE : 클래스 선언문에 쓸 수 있음.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // 세션값을 가져오는 기능 > 컨트롤러, 메서드에서 필요 : 반복 코드를 막기 위해 어노테이션 기반으로 개선.
}
