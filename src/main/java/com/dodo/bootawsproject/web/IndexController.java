package com.dodo.bootawsproject.web;

import com.dodo.bootawsproject.config.auth.LoginUser;
import com.dodo.bootawsproject.config.auth.dto.SessionUser;
import com.dodo.bootawsproject.service.posts.PostsService;
import com.dodo.bootawsproject.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지 이동과 관련된 url 매핑은 이 컨트롤러에서 컨트롤.

    private final PostsService postsService;
    private final HttpSession httpSession;

    // @LoginUser : 어느 컨트롤러든 annotation으로 세션 정보에서 User를 가져올 수 있음.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
