package com.dodo.bootawsproject.service.posts;

import com.dodo.bootawsproject.domain.posts.Posts;
import com.dodo.bootawsproject.domain.posts.PostsRepository;
import com.dodo.bootawsproject.web.dto.PostsResponseDto;
import com.dodo.bootawsproject.web.dto.PostsSaveRequestDto;
import com.dodo.bootawsproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).
                getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) { // title, content update service : 더티 체킹만으로 update 쿼리 가능.
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id='"+id+"'"));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 없습니다. id='"+id+"'")); // db에서 entity가 쿼리해옴
        return new PostsResponseDto(entity); // controller로 보낼 dto에 담음.
    }
}
