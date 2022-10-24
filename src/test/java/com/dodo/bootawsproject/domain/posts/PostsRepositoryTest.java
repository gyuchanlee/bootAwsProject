package com.dodo.bootawsproject.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository repository;

    @After // 단위 테스트 끝날 때 마다 수행되는 메서드 지정. 전체 테스트 중 테스트 간 데이터 침범을 막기 위해 수행
    public void cleanup() {
        repository.deleteAll(); // 데이터 삭제.
        // 여러 테스트를 동시에 진행하면 테스트용 db인 h2에 데이터가 그대로 남아 오류가 발생할 수 있음.
    }

    @Test
    public void boardSaveRead() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        repository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("dodobird@gmail.com")
                .build());

        // when
        List<Posts> postsList = repository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntityTest() {
        // given
        LocalDateTime now = LocalDateTime.of(2022,10,24,0,0,0);
        repository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = repository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>> createdDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
