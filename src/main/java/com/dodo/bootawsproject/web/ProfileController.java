package com.dodo.bootawsproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController { // 무중단 배포 시에 8081 포트 / 8082 포트 쓸 지 판단하는 기준
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles()); // 현재 실행중인 ActivProfile을 모두 가져옴.
        List<String> realProfiles = Arrays.asList("real","real1","real2"); // 3가지 중 하나라도 담겨있으면 반환하도록 설정.
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
