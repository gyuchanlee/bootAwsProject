package com.dodo.bootawsproject.config.auth;

import com.dodo.bootawsproject.config.auth.dto.OAuthAttributes;
import com.dodo.bootawsproject.config.auth.dto.SessionUser;
import com.dodo.bootawsproject.domain.user.Users;
import com.dodo.bootawsproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    // 소셜 로그인 후 가져온 사용자의 정보를 기반으로 가입 / 정보 수정 / 세션 저장
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 현재 로그인 진행 중인 서비스를 구분하는 코드, 구글/네이버/카카오 연동 구분
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        // OAuth2 로그인 진행 시, 키가 되는 필드값(PK). 구글은 기본 지원하지만 네이버/카카오는 설정해야함.

        // Service를 통해 가져온 OAuth2User의 attribute를 담을 클래스 > 모든 소셜 로그인에 사용.
        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Users user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        // 세션에 사용자 정보를 저장하기 위한 Dto. User를 쓰지 않는 이유 : User 클래스에 직렬화가 구현되지 않았음.
        // User는 엔티티 > 직렬화 코드를 넣으면 관계를 통해 자식 엔티티도 가지게 되면 자식 또한 직렬화에 포함 > 성능 이슈, 부수 효과 발생.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                        attributes.getAttributes(),
                        attributes.getNameAttributeKey());
    }

    private Users saveOrUpdate(OAuthAttributes attributes) {
        Users user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
